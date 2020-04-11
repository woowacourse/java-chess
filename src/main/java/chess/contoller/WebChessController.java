package chess.contoller;

import chess.dao.ChessBoardDao;
import chess.dao.ChessGameDao;
import chess.dao.DataAccessException;
import chess.domain.*;
import chess.domain.position.Position;
import chess.dto.ChessBoardAssembler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class WebChessController {
	private int gameId;
	private ChessBoardDao chessBoardDao;
	private ChessGameDao chessGameDao;

	public WebChessController() {
		chessBoardDao = new ChessBoardDao();
		chessGameDao = new ChessGameDao();
	}

	public String startGame(Request request, Response response) {
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		gameId = chessGameDao.add(chessGame.getTurn());
		chessBoardDao.addPieces(chessGame.getChessBoard(), gameId);
		return render(chessGame);
	}

	private ChessGame getChessGame() {
		ChessBoard chessBoard = chessBoardDao.findByGameId(gameId);
		return new ChessGame(chessBoard, chessGameDao.findTurnByGameId(gameId));
	}

	public String continueGame(Request request, Response response) throws RuntimeException {
		try {
			gameId = Integer.parseInt(request.queryParams("gameId"));
		} catch (NumberFormatException e) {
			throw new DataAccessException("게임 id(숫자)를 입력해주세요.");
		}
		ChessGame chessGame = getChessGame();
		return render(chessGame);
	}

	public String runGame(Request request, Response response) {
		ChessGame chessGame = getChessGame();
		chessGame.move(Position.of(request.queryParams("source")), Position.of(request.queryParams("target")));
		chessBoardDao.deleteByGameId(gameId);
		chessBoardDao.addPieces(chessGame.getChessBoard(), gameId);
		chessGameDao.updateTurn(gameId, chessGame.getTurn());
		if (chessGame.isEnd()) {
			chessGameDao.deleteByGameId(gameId);
			return renderEnd(chessGame);
		}
		return render(chessGame);
	}

	public String showGame(Request request, Response response) {
		ChessGame chessGame = getChessGame();
		return render(chessGame);
	}

	public String endGame(Request request, Response response) {
		ChessGame chessGame = getChessGame();
		chessGame.end();
		chessBoardDao.deleteByGameId(gameId);
		return renderEnd(chessGame);
	}

	private String render(ChessGame chessGame) {
		ChessStatus chessStatus = chessGame.createStatus();
		Map<String, Object> model = new HashMap<>();
		model.put("piece", ChessBoardAssembler.create(chessGame.getChessBoard()));
		model.put("blackscore", chessStatus.calculateScore(Side.BLACK));
		model.put("whitescore", chessStatus.calculateScore(Side.WHITE));
		model.put("turn", chessGame.getTurn());
		model.put("id", gameId);
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "game.html"));
	}

	private String renderEnd(ChessGame chessGame) {
		Map<String, Object> model = new HashMap<>();
		model.put("turn", chessGame.getTurn());
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, "end.html"));
	}
}
