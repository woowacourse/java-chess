package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.GameManager;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.util.WebOutputRenderer;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIApplication {
	public static void main(String[] args) {
		Spark.staticFileLocation("static");
		GameManager gameManager = new GameManager(BoardFactory.create());

		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("pieces", WebOutputRenderer.toModel(gameManager.getBoard()));
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
			return render(model, "board.html");
		});
	}

	public static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
