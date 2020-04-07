package chess.controller;

import chess.domain.game.ChessGame;
import chess.service.ChessWebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static chess.JsonTransformer.json;
import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
	private ChessWebService chessWebService;
	private ChessGame chessGame;

	public ChessWebController() {
		this.chessGame = new ChessGame();
		this.chessWebService = new ChessWebService();
	}

	public void run() {
		get("/", (req, res) -> {
			return render(chessWebService.index(chessGame), "index.html");
		});

		get("/new", (req, res) -> {
			return render(chessWebService.startGame(chessGame), "chess.html");
		});

		get("/loading", (req, res) -> {
			return render(chessWebService.loadGame(chessGame), "chess.html");
		});

		get("/board", (req, res) -> {
			return chessWebService.setBoard(chessGame);
		}, json());

		post("/board", (req, res) -> {
			Map<String, Object> model = chessWebService.move(req.queryParams("start"), req.queryParams("end"), chessGame);
			return render(model, (String) model.get("destination"));
		});

		post("/start", (req, res) -> {
			return chessWebService.chooseFirstPosition(req.queryParams("start"), chessGame);
		}, json());

		post("/end", (req, res) -> {
			return chessWebService.chooseSecondPosition(req.queryParams("end"));
		}, json());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
