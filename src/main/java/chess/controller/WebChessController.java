package chess.controller;

import static chess.jsonutil.JsonUtil.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.position.Position;
import chess.service.GameService;
import chess.view.dto.requestdto.PositionRequestDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
	private final GameService gameService;

	public WebChessController(GameService gameService) {
		this.gameService = gameService;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	public void run() {
		get("/", (req, res) -> render(new HashMap<>(), "index.html"));

		get("/chess/start", (req, res) -> {
			gameService.startNewGame();
			return gameService.getCurrentState();
		}, json());

		get("/chess/end", (req, res) -> {
			gameService.endGame();
			return gameService.getCurrentState();
		}, json());

		get("/chess/state", (req, res) -> gameService.getCurrentState(), json());

		get("/chess/pieces", (req, res) -> gameService.findAllPiecesOnBoard(), json());

		get("/chess/record", (req, res) -> gameService.calculateScore(), json());

		post("/chess/move", (req, res) -> {
			PositionRequestDTO request = fromJson(req.body(), PositionRequestDTO.class);
			gameService.move(Position.of(request.getFrom()), Position.of(request.getTo()));
			return gameService.findChangedPiecesOnBoard(Position.of(request.getFrom()), Position.of(request.getTo()));
		}, json());

		get("/chess/game/isnotfinish", (req, res) -> gameService.isNotFinish(), json());

		get("/chess/result/winner", (req, res) -> gameService.getWinner(), json());
	}
}
