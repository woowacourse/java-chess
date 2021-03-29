package chess;

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

		get("/menu", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
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
