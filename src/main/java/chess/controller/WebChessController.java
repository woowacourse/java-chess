package chess.controller;

import static spark.Spark.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import chess.service.ChessService;
import chess.web.dao.JdbcChessHistoryDao;
import chess.web.dto.ChessBoardDto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

	private final ChessService chessService;

	public WebChessController() {
		this.chessService = new ChessService(new JdbcChessHistoryDao());
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}

	private String renderStart(final Request request, final Response response) {
		return render(new HashMap<>(), "index.html");
	}

	public void start() {
		ChessGame chessGame = chessService.loadChessGame();

		get("/", this::renderStart);
		get("/chess", (request, response) -> renderChessBoard(chessGame));
	}

	private String renderChessBoard(final ChessGame chessGame) {
		ChessBoardDto chessBoardDto = chessGame.getChessBoardDto();
		Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoard());

		model.put("turns", chessGame.getCurrentTurnPieceColorDto());
		model.put("statuses", chessGame.getChessStatusDtos());
		return render(model, "chess.html");
	}

	public void run() {
		post("/chessMove", this::playChessGame);
		post("/chessRestart", this::restartChessGame);
		post("/chessEnd", this::endChessGame);
	}

	private String playChessGame(final Request request, final Response response) {
		ChessGame chessGame = chessService.loadChessGame();
		String sourcePosition = request.queryParams("sourcePosition").trim();
		String targetPosition = request.queryParams("targetPosition").trim();
		moveChessPiece(chessGame, sourcePosition, targetPosition);

		if (chessGame.isEndState()) {
			return renderChessEnd(chessGame);
		}
		return renderChessBoard(chessGame);
	}

	private void moveChessPiece(final ChessGame chessGame, final String sourcePosition, final String targetPosition) {
		List<String> commandArguments = Arrays.asList("move", sourcePosition, targetPosition);
		ChessCommand chessCommand = ChessCommand.of(commandArguments);

		chessGame.move(chessCommand);
		chessService.moveChessPiece(sourcePosition, targetPosition);
	}

	private String renderChessEnd(final ChessGame chessGame) {
		ChessBoardDto chessBoardDto = chessGame.getChessBoardDto();

		Map<String, Object> model = new HashMap<>(chessBoardDto.getChessBoard());
		model.put("is_king_caught", chessGame.isKingCaught());
		model.put("piece_color", chessGame.getCurrentTurnPieceColorDto());
		model.put("statuses", chessGame.getChessStatusDtos());

		return render(model, "result.html");
	}

	private String restartChessGame(final Request request, final Response response) {
		chessService.deleteChessGame();
		ChessGame chessGame = chessService.loadChessGame();

		return renderChessBoard(chessGame);
	}

	private String endChessGame(final Request request, final Response response) {
		ChessGame chessGame = chessService.loadChessGame();
		chessGame.end();

		return renderChessEnd(chessGame);
	}

}
