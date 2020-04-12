package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.service.GameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
	public static void main(String[] args) {
		initialize();
		start();
		running();
		end();
		status();
	}

	private static void initialize() {
		get("/", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});
	}

	private static void start() {
		get("/start", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			putBoardTo(model);
			return render(model, "start.html");
		});
	}

	private static void running() {
		get("/move", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			movePiece(req.queryParams("source"), req.queryParams("target"), model);
			GameService gameService = new GameService();
			if (gameService.isKingDie()) {
				res.redirect("/end");
			}
			putBoardTo(model);
			return render(model, "start.html");
		});
	}

	private static void end() {
		get("/end", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			GameService gameService = new GameService();
			gameService.makeNewGame();
			return render(model, "end.html");
		});
	}

	private static void status() {
		get("/status", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			GameService gameService = new GameService();
			double[] status = gameService.status();

			model.put("whiteStatus", String.valueOf(status[0]));
			model.put("blackStatus", String.valueOf(status[1]));
			return render(model, "status.html");
		});
	}

	private static void putBoardTo(Map<String, Object> model) {
		GameService gameService = new GameService();
		try {
			gameService.loadBoard()
				.forEach((key, value) -> {
					if (value == null) {
						model.put(key.getPosition(), "");
						return;
					}
					model.put(key.getPosition(), value.getSymbol());
				});
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
	}

	private static void movePiece(String source, String target, Map<String, Object> model) {
		try {
			GameService gameService = new GameService();
			gameService.movePiece(source, target);
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
