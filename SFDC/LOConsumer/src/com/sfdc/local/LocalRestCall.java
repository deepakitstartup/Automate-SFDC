package com.sfdc.local;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.google.gson.Gson;

 

public class LocalRestCall
{
String authURL = "https://test.salesforce.com/services/oauth2/authorize";
String tokenURL = "https://anzdemo--anzdev.cs20.my.salesforce.com/services/oauth2/token";
public static void main(String[] args) throws ClientProtocolException, IOException
{
	LocalRestCall rest = new LocalRestCall();
}

public LocalRestCall() throws ClientProtocolException, IOException
{
HttpClient httpclient = new DefaultHttpClient();
HttpPost post = new HttpPost(tokenURL);
post.getParams().setParameter("client_id", "3MVG9RHx1QGZ7OsgHeqJwgJREnZ.VRAKg1vzHj8DejgDcsU2O6yD21UMKFJaC.kmJM6xM1JSlfm258g5HaSRO");
post.getParams().setParameter("client_secret", "9128977782551395787");
post.getParams().setParameter("grant_type", "password");
post.getParams().setParameter("username", "deepak.sharma@anzbankdemo.accenture.com.anzdev");
post.getParams().setParameter("password", "$NoshiHaisha2urQnQPRCRckZI0iW45xgtyudi");
//post.getParams().setParameter("redirect_uri", "https://test.salesforce.com/services/oauth2/success");
System.out.println("The post request is :::"+ post.getURI()+";;;;"+post.toString()
		+ post.getParams() +post.getRequestLine() );

HttpResponse response = httpclient.execute(post);

JSONObject json = (JSONObject) JSONValue.parse(new InputStreamReader (response.getEntity().getContent()));
System.out.println(json);
}
}

