package chess;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private static final JsonTransformer instance = new JsonTransformer();

    private final Gson gson;

    private JsonTransformer() {
        this.gson = new Gson();
    }

    public static JsonTransformer getInstance() {
        return instance;
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
