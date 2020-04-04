import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.GameManager;
import chess.board.Location;
import chess.piece.PieceFactory;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	private Gson gson = new Gson();
	private GameManager gameManager = new GameManager(new PieceFactory().createPieces());

	public void run() {
		port(8080);
		staticFileLocation("/public");

		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/move", ((request, response) -> {
			String now = request.queryParams("now");
			String destination = request.queryParams("destination");
			gameManager.movePiece(Location.of(now), Location.of(destination));
			return gameManager;
		}), gson::toJson);

		get("/board", (request, response) -> gameManager, gson::toJson);
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
