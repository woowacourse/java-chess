package chess;

import org.junit.Rule;
import org.junit.jupiter.api.Test;

import com.despegar.http.client.HttpClientException;
import com.despegar.sparkjava.test.SparkServer;
import spark.servlet.SparkApplication;

class WebUIChessApplicationTest {
	public static class WebAppTestSparkApp implements SparkApplication {
		@Override
		public void init() {
			WebUIChessApplication.main(null);
		}
	}

	@Rule
	public static SparkServer<WebAppTestSparkApp> testServer = new SparkServer<>(WebAppTestSparkApp.class, 8080);

	@Test
	public void when_request_record_with_readyState_then_fail_test() throws HttpClientException {
		// GetMethod request = testServer.get("/chess/record", false);
		// HttpResponse httpResponse = testServer.execute(request);
		// assertThat(httpResponse.code()).isEqualTo(500);
	}
}