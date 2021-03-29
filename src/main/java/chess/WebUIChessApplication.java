package chess;

import chess.domain.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/registration", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "registration.html");
        });

		post("/menu", (req, res) -> {
            User user = new User(req.queryParams("id"), req.queryParams("pwd"));
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
			return render(model, "menu.html");
		});

        get("/game/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

		get("/game/saved", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "game.html");
		});
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
