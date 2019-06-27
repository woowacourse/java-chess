package chess.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class MainController {
    public Object main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }
}
