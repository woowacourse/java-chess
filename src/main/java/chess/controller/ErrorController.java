package chess.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class ErrorController {
    public Object exception(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", req.queryParams("message"));
        return render(model, "error.html");
    }
}
