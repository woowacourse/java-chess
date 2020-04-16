package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.service.ChessService;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.ChessGameDto;
import chess.service.dto.ChessStatusDtos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessService chessService;

	public WebChessController(final ChessService chessService) {
		Objects.requireNonNull(chessService, "체스 서비스가 null입니다.");
		this.chessService = chessService;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private String renderStart(final Request request, final Response response) {
		return render(new HashMap<>(), "index.html");
	}

	public void run() {
		get("/", this::renderStart);
		get("/chess", (request, response) -> render(chessService.loadChessGame()));

		post("/chess_play", this::playChessGame);
		post("/chess_new", this::newChessGame);
		post("/chess_end", this::endChessGame);
	}

	private String render(final ChessGameDto chessGameDto) {
		final ChessBoardDto chessBoardDto = chessGameDto.getChessBoardDto();
		final ChessStatusDtos chessStatusDtos = chessGameDto.getChessStatusDtos();

		final Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoard());
		model.put("piece_color", chessGameDto.getPieceColorDto());
		model.put("status", chessStatusDtos.getChessStatusDtos());
		return render(model, "chess.html");
	}

	private String playChessGame(final Request request, final Response response) {
		final String sourcePosition = request.queryParams("sourcePosition").trim();
		final String targetPosition = request.queryParams("targetPosition").trim();
		final ChessGameDto chessGameDto = chessService.playChessGame(sourcePosition, targetPosition);

		if (chessGameDto.isEndState()) {
			return renderEnd(chessGameDto);
		}
		return render(chessGameDto);
	}

	private String renderEnd(final ChessGameDto chessGameDto) {
		final ChessBoardDto chessBoardDto = chessGameDto.getChessBoardDto();
		final ChessStatusDtos chessStatusDtos = chessGameDto.getChessStatusDtos();

		final Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoard());
		model.put("is_king_caught", chessGameDto.isKingCaught());
		model.put("piece_color", chessGameDto.getPieceColorDto());
		model.put("status", chessStatusDtos.getChessStatusDtos());
		return render(model, "result.html");
	}

	private String newChessGame(final Request request, final Response response) {
		return render(chessService.createChessGame());
	}

	private String endChessGame(final Request request, final Response response) {
		return renderEnd(chessService.endChessGame());
	}

}
