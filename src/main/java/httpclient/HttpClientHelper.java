package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

public class HttpClientHelper {

	public HttpResponse executeGetRequest(URL url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpGet httpget = httpGetRequest(url, params);
		HttpResponse response = HttpClient.getClient().execute(httpget);
		return response;

	}

	public HttpResponse executePostRequest(URL url, Map<String, String> params, String json)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpPost httpPost = httpPostRequest(url, params, json);
		HttpResponse response = HttpClient.getClient().execute(httpPost);
		return response;
	}
	
	public HttpResponse executeGetRequestWithTimeout(URL url,
			Map<String, String> params, int timeout) throws URISyntaxException,
			ClientProtocolException, IOException {
		HttpGet httpget = httpGetRequest(url, params);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();
		httpget.setConfig(requestConfig);
		HttpResponse response = HttpClient.getClient().execute(httpget);

		return response;

	}

	private HttpGet httpGetRequest(URL url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		URIBuilder builder = new URIBuilder();
		builder.setScheme(url.getProtocol()).setHost(url.getHost())
				.setPath(url.getPath());
		for (Entry<String, String> entry : params.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);
		return httpget;
	}
	private HttpPost httpPostRequest(URL url, Map<String, String> params, String json) throws URISyntaxException, IOException{
		URIBuilder builder = new URIBuilder();
		builder.setScheme(url.getProtocol()).setHost(url.getHost())
				.setPath(url.getPath());
		URI uri = builder.build();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new StringEntity(json));
		for (Entry<String, String> entry : params.entrySet()) {
			if(entry.getKey().toString().equals("Content-type")){
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
			
		}
		return httpPost;
	}
}
