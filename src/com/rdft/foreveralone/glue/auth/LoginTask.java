package com.rdft.foreveralone.glue.auth;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.rdft.foreveralone.glue.debug.DebugConfig;

public class LoginTask extends AsyncTask<Void, Void, DefaultHttpClient> {
	Activity parent;
	DefaultHttpClient authenticatedClient;
	String TAG = "LoginTask";
	
	public LoginTask(Activity activity) {
		parent = activity;
	}

	private String getAuthToken() throws OperationCanceledException,
			AuthenticatorException, IOException {
		String authToken = null;
		
		AccountManager mgr = AccountManager.get(parent);
		Account[] accts = mgr.getAccountsByType("com.google");
		Account account = accts[0];
		DebugConfig.logInfo(TAG, account.name);
		AccountManagerFuture<Bundle> accountManagerFuture = mgr.getAuthToken(
				account, "ah", true, null, null);
		Bundle authTokenBundle = accountManagerFuture.getResult();
		
		Intent intent = (Intent) authTokenBundle.get(AccountManager.KEY_INTENT);
		if (intent != null) {
			parent.startActivity(intent);
		} else {
			authToken = authTokenBundle.get(AccountManager.KEY_AUTHTOKEN)
				.toString();
		}
		return authToken;
	}

	private DefaultHttpClient getAuthenticatedClient(String authToken)
			throws LoginException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(DebugConfig.getURL("/_ah/login?email=lugkhast%40gmail.com&admin=False&action=Login&continue=http://localhost/&auth=" + authToken));
		HttpResponse response;
		boolean success = false;

		try {
			// client shouldn't follow redirects for now
			client.getParams().setBooleanParameter(
					ClientPNames.HANDLE_REDIRECTS, false);
			response = client.execute(request);

			if (response.getStatusLine().getStatusCode() != 302) {
				/* The response should be a redirect */
				DebugConfig.logError(TAG, "Cookie request failed!");
				// throw new LoginException();
			}

			for (Cookie cookie : client.getCookieStore().getCookies()) {
				/*
				if (cookie.getName().equals("ACSID")) {
					success = true;
					DebugConfig.logInfo(TAG, "Successfully retrieved cookie!");
				}
				*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Revert the setting
		client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS,
				true);
		return client;
	}

	@Override
	protected DefaultHttpClient doInBackground(Void... stuff) {
		String authToken = null;
		DefaultHttpClient client = null;
		try {
			authToken = getAuthToken();
			client = getAuthenticatedClient(authToken);
		} catch (AuthenticatorException e) {
			e.printStackTrace();
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
	
	protected void onPostExecute(DefaultHttpClient client) {
		ILoginReceiver receiver = (ILoginReceiver) parent;
		receiver.onLoginComplete(client);
	}
	
	public interface ILoginReceiver {
		public void onLoginComplete(DefaultHttpClient client);
	}
}
