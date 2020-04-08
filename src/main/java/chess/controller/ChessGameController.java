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
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessGameController {
	private final ChessService chessService;

	public ChessGameController(ChessService chessService) {
		this.chessService = chessService;
	}

	public void run() {
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.handlebars");
		});

		post("/move", (request, response) -> {
			try {
				MoveRequestDto moveRequestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
				return toJson(chessService.move(moveRequestDto));
			} catch (PieceMoveFailedException exception) {
				return toJson(MoveResponseDto.ofFailedToMove(exception.getMessage()));
			}
		});

		get("/resume", (request, response) -> {
			BoardResponseDto boardResponseDto = chessService.resumeGame();
			return toJson(boardResponseDto);
		});

		get("/start", (request, response) -> {
			BoardResponseDto boardResponseDto = chessService.initializeBoard();
			return toJson(boardResponseDto);
		});
	}

	private String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private String toJson(Object object) {
		return new Gson().toJson(object);
	}
}
