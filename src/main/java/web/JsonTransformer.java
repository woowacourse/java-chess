package web;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson = new Gson();

    @Override
    public String render(final Object model) {
        return gson.toJson(model);
    }

    public Gson getGson() {
        return gson;
    }
}
