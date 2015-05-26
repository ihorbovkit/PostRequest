package httpclient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClient {

	private static CloseableHttpClient client;

	private HttpClient() {

	}

	public static CloseableHttpClient getClient() {
		if (client == null) {
			client = HttpClientBuilder.create().build();
		}
		return client;
	}

}
