package chess;

import chess.controller.ChessWebController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class ChessWebUIApplication {
	public static void main(String[] args) {
		ChessWebController chessWebController = new ChessWebController();

		staticFiles.location("/public");

		get("/", (req, res) -> {
			return render(chessWebController.index(), "index.html");
		});

		get("/new", (req, res) -> {
			return render(chessWebController.startGame(), "chess.html");
		});

		get("/loading", (req, res) -> {
			return render(chessWebController.loadGame(), "chess.html");
		});

		get("/board", (req, res) -> {
			return chessWebController.setBoard();
		}, json());

		post("/board", (req, res) -> {
			return render(chessWebController.move(req.queryParams("start"), req.queryParams("end")), "chess.html");
		});

		post("/start", (req, res) -> {
			return chessWebController.chooseFirstPosition(req.queryParams("start"));
		}, json());

		post("/end", (req, res) -> {
			return chessWebController.chooseSecondPosition(req.queryParams("end"));
		}, json());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

