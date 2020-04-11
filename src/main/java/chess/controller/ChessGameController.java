package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import chess.dto.BoardResponseDto;
import chess.dto.MoveRequestDto;
import chess.dto.MoveResponseDto;
import chess.exception.PieceMoveFailedException;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessGameController {
	private final ChessService chessService;
	private final Gson gson;

	public ChessGameController(ChessService chessService, Gson gson) {
		this.chessService = chessService;
		this.gson = gson;
	}

	public void run() {
		get("/", (request, response) -> render(new HashMap<>(), "index.handlebars"));
		post("/move", (request, response) -> gson.toJson(move(request), MoveResponseDto.class));
		get("/resume", (request, response) -> gson.toJson(chessService.resumeGame(), BoardResponseDto.class));
		get("/start", (request, response) -> gson.toJson(chessService.initializeBoard(), BoardResponseDto.class));
	}

	private MoveResponseDto move(Request request) {
		try {
			MoveRequestDto moveRequestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
			return chessService.move(moveRequestDto);
		} catch (PieceMoveFailedException exception) {
			return MoveResponseDto.ofFailedToMove(exception.getMessage());
		}
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}
