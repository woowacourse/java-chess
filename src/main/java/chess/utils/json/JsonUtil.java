package chess.utils.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import spark.ResponseTransformer;

public class JsonUtil {
	private static final Gson GSON = new Gson();

	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	public static JsonElement toJsonTree(Object object) {
		return GSON.toJsonTree(object);
	}

	public static <T> T fromJson(String data, Class<T> tClass) {
		return GSON.fromJson(data, tClass);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}
