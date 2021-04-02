package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		port(8080);
		staticFiles.location("/templates");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/chess/start", (req, res) ->
			"[{\"position\":{\"row\": 4, \"col\": 3}, \"piece\": {\"symbol\": \"p\", \"team\": \"black\"}},{ \"position\": {\"row\": 3, \"col\": 3},\"piece\":{\"symbol\":\"\",\"team\":\"empty\"}}]"
		);

		get("/chess/record", (req, res) ->
			"[{\"team\":\"black\",\"score\":35.5},{\"team\":\"white\",\"score\":25}]"
		);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
