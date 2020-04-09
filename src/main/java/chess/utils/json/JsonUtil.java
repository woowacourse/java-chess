package chess.utils.json;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil {
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static <T> T fromJson(String data, Class<T> tClass) {
		return new Gson().fromJson(data, tClass);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}
