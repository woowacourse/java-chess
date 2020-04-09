package chess.controller;

import static chess.utils.json.JsonUtil.*;
import static chess.view.response.ResponseStatus.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import chess.service.GameService;
import chess.view.dto.requestdto.PositionRequestDTO;
import chess.view.response.StandardResponse;
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

		get("/chess/state", (req, res) ->
				makeResponse(() ->
					new StandardResponse(SUCCESS, toJsonTree(gameService.getCurrentState()))),
			json()
		);

		post("/chess/state", (req, res) ->
				makeResponse(() -> {
					gameService.changeState(req.body());
					return new StandardResponse(SUCCESS);
				}),
			json()
		);

		get("/chess/pieces", (req, res) ->
				makeResponse(() ->
					new StandardResponse(SUCCESS, toJsonTree(this.gameService.findAllPiecesOnBoard()))
				),
			json()
		);

		get("/chess/record", (req, res) ->
				makeResponse(() ->
					new StandardResponse(SUCCESS, toJsonTree(this.gameService.calculateScore()))
				),
			json()
		);

		post("/chess/move", (req, res) ->
				makeResponse(() -> {
					PositionRequestDTO requestDTO = fromJson(req.body(), PositionRequestDTO.class);
					this.gameService.move(requestDTO);
					return new StandardResponse(SUCCESS, toJsonTree(gameService.findChangedPiecesOnBoard(requestDTO)));
				}),
			json()
		);

		get("/chess/isnotfinish", (req, res) ->
				makeResponse(() ->
					new StandardResponse(SUCCESS, toJsonTree(this.gameService.isNotFinish()))
				),
			json()
		);

		get("/chess/result", (req, res) ->
				makeResponse(() ->
					new StandardResponse(SUCCESS, toJsonTree(this.gameService.getWinner()))
				),
			json()
		);
	}

	private StandardResponse makeResponse(Supplier<StandardResponse> responseGenerator) {
		try {
			return responseGenerator.get();
		} catch (RuntimeException e) {
			return new StandardResponse(ERROR, e.getMessage());
		}
	}
}
