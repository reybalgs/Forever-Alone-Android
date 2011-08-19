package com.rdft.foreveralone.glue.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import com.rdft.foreveralone.glue.debug.DebugConfig;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public class LoginManager {
	DefaultHttpClient http_client = new DefaultHttpClient();
	private Activity parentActivity;

	protected void onResume(Activity parentActivity) {
		this.parentActivity = parentActivity;
		Intent intent = parentActivity.getIntent();
		AccountManager accountManager = AccountManager.get(parentActivity
				.getApplicationContext());
		Account account = (Account) intent.getExtras().get("account");
		accountManager.getAuthToken(account, "ah", false,
				new GetAuthTokenCallback(), null);
	}

	private class GetAuthTokenCallback implements AccountManagerCallback {
		public void run(AccountManagerFuture result) {
			Bundle bundle;
			try {
				bundle = (Bundle) result.getResult();
				Intent intent = (Intent) bundle.get(AccountManager.KEY_INTENT);
				if (intent != null) {
					// User input required
					parentActivity.startActivity(intent);
				} else {
					onGetAuthToken(bundle);
				}
			} catch (OperationCanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	protected void onGetAuthToken(Bundle bundle) {
		String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
		new GetCookieTask().execute(auth_token);
	}

	private class GetCookieTask extends AsyncTask<String, Void, Boolean> {
		protected Boolean doInBackground(String... tokens) {
			try {
				// Don't follow redirects
				http_client.getParams().setBooleanParameter(
						ClientPNames.HANDLE_REDIRECTS, false);

				HttpGet http_get = new HttpGet(
						DebugConfig
								.getURL("_ah/login?continue=http://localhost/&auth="
										+ tokens[0]));
				HttpResponse response;
				response = http_client.execute(http_get);
				if (response.getStatusLine().getStatusCode() != 302)
					// Response should be a redirect
					return false;

				for (Cookie cookie : http_client.getCookieStore().getCookies()) {
					if (cookie.getName().equals("ACSID"))
						return true;
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				http_client.getParams().setBooleanParameter(
						ClientPNames.HANDLE_REDIRECTS, true);
			}
			return false;
		}
		
		protected void onPostExecute(Boolean result) {
			ILoginCallback callback = (ILoginCallback) parentActivity;
			callback.onLoginComplete(http_client);
		}
	}

	private class AuthenticatedRequestTask extends AsyncTask {
		@SuppressWarnings("unused")
		protected HttpResponse doInBackground(String... urls) {
			try {
				HttpGet http_get = new HttpGet(urls[0]);
				return http_client.execute(http_get);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@SuppressWarnings("unused")
		protected void onPostExecute(HttpResponse result) {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(result.getEntity().getContent()));
				String first_line = reader.readLine();
				Toast.makeText(parentActivity.getApplicationContext(),
						first_line, Toast.LENGTH_LONG).show();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public interface ILoginCallback {
		public void onLoginComplete(HttpClient authenticatedClient);
	}
}
