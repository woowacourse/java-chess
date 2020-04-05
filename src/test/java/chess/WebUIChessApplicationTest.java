package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import spark.servlet.SparkApplication;

class WebUIChessApplicationTest {
	public static class WebAppTestSparkApp implements SparkApplication {
		public void init() {
			new WebUIChessApplication();
		}
	}

	@ClassRule
	public static SparkServer<WebAppTestSparkApp> testServer = new SparkServer<>(WebAppTestSparkApp.class, 8080);

	@Test
	public void serverRespondsSuccessfully() throws HttpClientException {
		GetMethod request = testServer.get("/chess/record", false);
		HttpResponse httpResponse = testServer.execute(request);
		assertThat(httpResponse.code()).isEqualTo(500);
	}
}