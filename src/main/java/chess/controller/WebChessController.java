package chess.controller;

import static spark.Spark.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.chessBoard.dto.ChessBoardDto;
import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessGame chessGame;

	public WebChessController(final ChessGame chessGame) {
		this.chessGame = chessGame;
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	public void start() {
		get("/", this::renderStart);
		get("/chess", this::renderChessBoard);
	}

	private String renderStart(final Request request, final Response response) {
		return render(new HashMap<>(), "index.html");
	}

	private String renderChessBoard(final Request request, final Response response) {
		ChessBoardDto chessBoardDto = chessGame.getChessBoardDto();

		Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoardDto());
		model.put("turn", chessGame.getCurrentTurnColor());
		model.put("statuses", chessGame.getChessStatusDtos());
		return render(model, "chess.html");
	}

	public void run() {
		post("/chessMove", this::playChessGame);
		post("/chessEnd", this::endChessGame);
	}

	private String playChessGame(final Request request, final Response response) {
		List<String> commandArguments = Arrays.asList(
			"move",
			request.queryParams("sourcePosition").trim(),
			request.queryParams("targetPosition").trim());
		ChessCommand chessCommand = ChessCommand.of(commandArguments);
		chessGame.move(chessCommand);

		if (chessGame.isEndState()) {
			return renderChessEnd(request, response);
		}
		return renderChessBoard(request, response);
	}

	private String renderChessEnd(final Request request, final Response response) {
		ChessBoardDto chessBoardDto = chessGame.getChessBoardDto();

		Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoardDto());
		model.put("is_king_caught", chessGame.isKingCaught());
		model.put("color", chessGame.getCurrentTurnColor().getColor());
		model.put("statuses", chessGame.getChessStatusDtos());

		return render(model, "result.html");
	}

	private String endChessGame(final Request request, final Response response) {
		chessGame.end();
		return renderChessEnd(request, response);
	}

}
