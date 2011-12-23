package com.rdft.foreveralone.glue.auth;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.rdft.foreveralone.glue.FaHttpClient;
import com.rdft.foreveralone.glue.debug.DebugConfig;

/**
 * LoginTask attempts to create a FaHttpClient which is authenticated to the
 * server as the user.
 * 
 * @author lugkhast
 * 
 */
public class LoginTask extends AsyncTask<Void, Void, FaHttpClient> {
	Activity parent;
	DefaultHttpClient authenticatedClient;
	String TAG = "LoginTask";
	boolean success;

	public LoginTask(Activity activity) {
		parent = activity;
	}

	private String getAuthToken() throws OperationCanceledException,
			AuthenticatorException, IOException {
		String authToken = null;

		AccountManager mgr = AccountManager.get(parent);
		Account[] accts = mgr.getAccountsByType("com.google");
		if (accts.length > 0) {
			Account account = accts[0];
			DebugConfig.logInfo(TAG, account.name);
			AccountManagerFuture<Bundle> accountManagerFuture = mgr
					.getAuthToken(account, "ah", true, null, null);
			Bundle authTokenBundle = accountManagerFuture.getResult();

			Intent intent = (Intent) authTokenBundle
					.get(AccountManager.KEY_INTENT);
			if (intent != null) {
				parent.startActivity(intent);
			} else {
				authToken = authTokenBundle.get(AccountManager.KEY_AUTHTOKEN)
						.toString();
			}
		} else if (DebugConfig.ALLOW_FAKE_ACCOUNTS) {
			DebugConfig.logError(TAG, "No accounts found! Now "
					+ "assuming server is App Engine dev appserver.");
			authToken = "abcde";
		}

		return authToken;
	}

	private FaHttpClient getAuthenticatedClient(String authToken)
			throws LoginException, SocketTimeoutException {
		FaHttpClient client = new FaHttpClient();
		HttpGet request = new HttpGet(
				DebugConfig
						.getURL("/_ah/login?email=lugkhast%40gmail.com&admin=False&action=Login&continue=http://localhost/&auth="
								+ authToken));
		HttpResponse response;
		boolean success = false;

		try {
			// client shouldn't follow redirects for now
			HttpParams params = client.getParams();
			params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);

			// We shouldn't take forever to log in. Give up early.
			// Timeout given is in milliseconds.
			int timeout = 5000;
			HttpConnectionParams.setConnectionTimeout(params, timeout);
			HttpConnectionParams.setSoTimeout(params, timeout);
			response = client.execute(request);

			if (response.getStatusLine().getStatusCode() != 302) {
				/* The response should be a redirect */
				DebugConfig.logError(TAG, "Cookie request failed!");
				throw new LoginException();
			}

			for (Cookie cookie : client.getCookieStore().getCookies()) {
				/*
				 * if (cookie.getName().equals("ACSID")) { success = true;
				 * DebugConfig.logInfo(TAG, "Successfully retrieved cookie!"); }
				 */
			}
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Revert the setting
		client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS,
				true);

		this.success = success;
		if (success) {
			return client;
		} else {
			DebugConfig
					.logWarning(TAG,
							"Authentication failed - not returning unauth'd HttpClient");
			throw new SocketTimeoutException(
					"Failed to connect to server for login");
		}
	}

	@Override
	protected FaHttpClient doInBackground(Void... stuff) {
		String authToken = null;
		FaHttpClient client = null;
		try {
			authToken = getAuthToken();
			client = getAuthenticatedClient(authToken);
		} catch (AuthenticatorException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (OperationCanceledException e) {
			DebugConfig.logInfo(TAG, "The user cancelled authentication.");
		} catch (LoginException e) {
			DebugConfig.logError(TAG, "Login attempt failed!");
			e.printStackTrace();
		}

		return client;
	}

	protected void onPostExecute(FaHttpClient client) {
		ILoginReceiver receiver = (ILoginReceiver) parent;
		if (success) {
			receiver.onLoginComplete(client);
		} else {
			receiver.onConnectionFailed();
		}
	}

	public interface ILoginReceiver {
		public void onConnectionFailed();

		public void onLoginComplete(FaHttpClient client);
	}
}
