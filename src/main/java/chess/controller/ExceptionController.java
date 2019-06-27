package chess.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.web.TemplateEngine;
import spark.Request;
import spark.Response;

public class ExceptionController {
    public static void exception(RuntimeException e, Request req, Response res) {
        res.status(404);
        res.body(
                "<h1> 에러 발생 </h1>" +
                        "<div>" + e.getMessage() + "</div>" +
                        "<button onclick=\"window.history.back()\">되돌아가기</button>"
        );
    }

    public static String exception(SQLException e, Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("errorMessage", e.getMessage());
        return TemplateEngine.render(model, "error.html");
    }
}
