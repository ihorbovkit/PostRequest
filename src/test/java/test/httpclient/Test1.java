package test.httpclient;

import file.parser.FileToString;
import filewriter.WriteToFile;
import httpclient.HttpClientHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import parser.dto.phoneNumber.PhoneNumber;
import response.parsers.JsonParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Test1 {

	HttpClientHelper helper;
	PhoneNumber number;
	FileToString file;
	String accessToken;

	@BeforeTest
	public void setUp() {
		helper = new HttpClientHelper();
	}

	@Test
	public void Test1Registration() throws ClientProtocolException, IOException,
			URISyntaxException, JsonParseException,	JsonMappingException{
		URL testUrl = new URL("http", "54.93.222.229",
				"/api/v1/register");
		Map<String, String> params = new HashMap<String, String>();
		params.put("Content-type", "application/json");
		file = new FileToString();
		String s = file.fileToString("src/test/resources/PhoneNumber.json");
		
		/*Send first post request*/
		HttpResponse response = helper.executePostRequest(testUrl, params, s);
		JsonParser parser = new JsonParser();
		number = parser.readJsonToObject(PhoneNumber.class, response.getEntity().getContent());

		/*Send second post request*/
		accessToken = number.getAccessToken().toString();
		WriteToFile wtf = new WriteToFile();
		wtf.writeToFile(number.getAccessToken());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Status Code");
	}
	@Test
	public void Test2RegistrationConfirm() throws IOException, URISyntaxException, IOException{
		URL testUrl = new URL("http", "54.93.222.229",
				"/api/v1/register_confirm");
		Map<String, String> params = new HashMap<String, String>();
		params.put("Content-type", "application/json");

		helper.executePostRequest(testUrl, params, number.toString());
		String s2 = file.fileToString("src/test/resources/result.json");
		HttpResponse response = helper.executePostRequest(testUrl, params, s2);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200, "Status Code");
	}
	@Test
	public void Test3SendImage() throws IOException, URISyntaxException{
		URL testUrl = new URL("http", "54.93.222.229",
				"/api/v1/register_files?accessToken" + accessToken);
		Map<String, String> params = new HashMap<String, String>();
		params.put("Content-type", "image/png");
	
		
	}
}