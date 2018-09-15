package com.sfdc.local;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;



public class AccessTokenRest extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException{
		System.out.println("Inside Servlet");
	String authURL = "https://login.salesforce.com/services/oauth2/authorize";
	String tokenURL = "https://anzdemo--anzdev.cs20.my.salesforce.com/services/oauth2/token";
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost post = new HttpPost(tokenURL);
	post.getParams().setParameter("client_id", "3MVG9RHx1QGZ7OsgHeqJwgJREnZ.VRAKg1vzHj8DejgDcsU2O6yD21UMKFJaC.kmJM6xM1JSlfm258g5HaSRO");
	post.getParams().setParameter("client_secret", "9128977782551395787");
	post.getParams().setParameter("grant_type", "password");
	post.getParams().setParameter("username", "deepak.sharma@anzbankdemo.accenture.com.anzdev");
	post.getParams().setParameter("password", "$NoshiHaisha2urQnQPRCRckZI0iW45xgtyudi");
	post.getParams().setParameter("redirect_uri", "https://test.salesforce.com/services/oauth2/success");
	try{
	HttpResponse response = httpclient.execute(post);
	JSONObject json = (JSONObject) JSONValue.parse(new InputStreamReader (response.getEntity().getContent()));
	System.out.println("The Server response is :::"+json);
	}
	
	catch(ClientProtocolException e)
	{
		
	}
	catch(IOException e)
	{
		
	}
	}


}

