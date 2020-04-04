import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.GameManager;
import chess.piece.PieceFactory;
import com.google.gson.Gson;
import service.GameManagerService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private Gson gson = new Gson();
	private GameManagerService gameManagerService = new GameManagerService();
	private GameManager gameManager = new GameManager(new PieceFactory().createPieces());

	public void run() {
		port(8080);
		staticFileLocation("/public");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		post("/move", ((request, response) -> {
			String now = request.queryParams("now");
			String destination = request.queryParams("destination");
			return gameManagerService.move(gameManager, now, destination);
		}), gson::toJson);

		get("/board", (request, response) -> gameManager, gson::toJson);

		get("/status", (request, response) -> gameManagerService.status(gameManager), gson::toJson);

		get("/winner", (request, response) -> gameManagerService.findWinner(gameManager), gson::toJson);
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
