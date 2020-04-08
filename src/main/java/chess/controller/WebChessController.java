package chess.controller;

import static chess.jsonutil.JsonUtil.*;
import static chess.jsonutil.ResponseStatus.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.position.Position;
import chess.jsonutil.StandardResponse;
import chess.service.GameService;
import chess.view.dto.requestdto.PositionRequestDTO;
import com.google.gson.Gson;
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

		get("/chess/state", (req, res) -> {
			try {
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(gameService.getCurrentState()));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());

		post("/chess/state", (req, res) -> {
			String request = req.body();
			if ("start".equals(request)) {
				try {
					gameService.startNewGame();
					return new StandardResponse(SUCCESS);
				} catch (RuntimeException e) {
					return new StandardResponse(ERROR, e.getMessage());
				}
			} else if ("end".equals(request)) {
				try {
					gameService.endGame();
					return new StandardResponse(SUCCESS);
				} catch (RuntimeException e) {
					return new StandardResponse(ERROR, e.getMessage());
				}
			} else {
				return new StandardResponse(ERROR, "유효한 명령이 아닙니다");
			}
		}, json());

		get("/chess/pieces", (req, res) -> {
			try {
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(gameService.findAllPiecesOnBoard()));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());

		get("/chess/record", (req, res) -> {
			try {
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(gameService.calculateScore()));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());

		post("/chess/move", (req, res) -> {
			try {
				PositionRequestDTO request = fromJson(req.body(), PositionRequestDTO.class);
				gameService.move(Position.of(request.getFrom()), Position.of(request.getTo()));
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(
					gameService.findChangedPiecesOnBoard(Position.of(request.getFrom()),
						Position.of(request.getTo()))));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());

		get("/chess/isnotfinish", (req, res) -> {
			try {
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(gameService.isNotFinish()));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());

		get("/chess/result", (req, res) -> {
			try {
				return new StandardResponse(SUCCESS, new Gson().toJsonTree(gameService.getWinner()));
			} catch (RuntimeException e) {
				return new StandardResponse(ERROR, e.getMessage());
			}
		}, json());
	}
}
