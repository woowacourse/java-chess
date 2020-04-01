package chess;

import chess.view.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        get("/form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("player1", new User(req.queryParams("player1_name")));
            model.put("player2", new User(req.queryParams("player2_name")));
            return render(model, "index3.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
