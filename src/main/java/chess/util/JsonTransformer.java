package chess.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public final class JsonTransformer implements ResponseTransformer {
    private final Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
