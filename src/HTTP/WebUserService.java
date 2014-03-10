package HTTP;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import PlaceItDB.UserDBModel;

public class WebUserService extends WebService implements UserDBModel{
	public HttpContext context;

	
	@Override
	public HttpContext signup(String username, String password, RequestReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		NameValuePair user = new BasicNameValuePair("pid", username);
		NameValuePair pass = new BasicNameValuePair("password", password);
		values.add(user);
		values.add(pass);
		CookieStore cookieStore = new BasicCookieStore();
		context = new BasicHttpContext();
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		new RequestTask(receiver,context, values).execute(SIGNUP_URL);
		return context;
	}

	@Override
	public HttpContext login(String username, String password, RequestReceiver receiver) {
		CookieStore cookieStore = new BasicCookieStore();
		context = new BasicHttpContext();
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		List<NameValuePair> login = new Vector<NameValuePair>();
		login.add(new BasicNameValuePair("pid", username));
		login.add(new BasicNameValuePair("password", password));
		new RequestTask(receiver,context, login).execute(LOGIN_URL);
		return context;
	}

	@Override
	public void logout(RequestReceiver receiver) {
		this.context = null;
		new RequestTask(receiver,context, new Vector<NameValuePair>()).execute(LOGOUT_URL);

	}

	@Override
	public boolean isUserLoggedIn() {
		return true;
		
	}

}
