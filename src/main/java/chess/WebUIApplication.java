package chess;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.GameManager;
import chess.domain.position.Position;
import chess.util.WebOutputRenderer;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIApplication {
	public static void main(String[] args) throws SQLException {
		Spark.staticFileLocation("static");
		final GameManager gameManager = new GameManager();

		get("/", (request, response) -> "index.html");

		get("/start", (request, response) -> {
			gameManager.resetGame();
			Map<String, Object> model = new HashMap<>();
			model.put("pieces", WebOutputRenderer.toModel(gameManager.getBoard()));
			model.put("turn", gameManager.getCurrentTurn().name());

			return render(model, "board.html");
		});

		get("/resume", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("pieces", WebOutputRenderer.toModel(gameManager.getBoard()));
			model.put("turn", gameManager.getCurrentTurn().name());

			return render(model, "board.html");
		});

		post("/move", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			try {
				gameManager.move(Position.of(request.queryParams("target")),
					Position.of(request.queryParams("destination")));
			} catch (RuntimeException e) {
				model.put("error", e.getMessage());
			}
			model.put("pieces", WebOutputRenderer.toModel(gameManager.getBoard()));
			model.put("turn", gameManager.getCurrentTurn().name());
			if (!gameManager.isKingAlive()) {
				model.put("winner", gameManager.getCurrentTurn().reverse());
				gameManager.resetGame();
				return render(model, "end.html");
			}
			return render(model, "board.html");
		});

		get("/status", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("pieces", WebOutputRenderer.toModel(gameManager.getBoard()));
			model.put("turn", gameManager.getCurrentTurn().name());
			model.put("scores", WebOutputRenderer.scoreToModel(gameManager.calculateEachScore()));

			return render(model, "board.html");
		});

		get("/end", (request, response) -> {
			gameManager.resetGame();
			return render(new HashMap<>(), "end.html");
		});
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
