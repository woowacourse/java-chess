import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.GameManager;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private static GameManager gameManager;
	private static Gson gson = new Gson();
	private static WebController webController = new WebController();

	public static void main(String[] args) {
		port(8080);
		staticFileLocation("/public");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		post("/move", (request, response) ->
				webController.move(request, gameManager)
			, gson::toJson);

		get("/start", (request, response) -> {
			gameManager = webController.start();
			return gameManager;
		}, gson::toJson);

		get("/resume", (request, response) -> {
			gameManager = webController.resume();
			return gameManager;
		}, gson::toJson);

		get("/status", (request, response) -> webController.status(gameManager), gson::toJson);
		get("/winner", (request, response) -> webController.winner(gameManager), gson::toJson);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
