package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.position.PositionFactory;
import chess.domain.web.MovingPosition;
import chess.service.ChessWebService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessWebController {
	private ChessWebService chessWebService;
	private ChessGame chessGame;

	public ChessWebController() {
		this.chessGame = new ChessGame();
		this.chessWebService = new ChessWebService();
	}

	public Map<String, Object> index() {
		Map<String, Object> model = new HashMap<>();

		chessWebService.resetGame(chessGame);
		model.put("status", true);

		return model;
	}

	public Map<String, Object> startGame() throws SQLException {
		Map<String, Object> model = new HashMap<>();

		chessWebService.resetGameAndHistory(chessGame);
		model.put("status", true);

		return model;
	}

	public Map<String, Object> loadGame() throws SQLException {
		Map<String, Object> model = new HashMap<>();
		model.put("status", true);

		List<MovingPosition> histories = chessWebService.selectAllHistory();

		for (MovingPosition movingPosition : histories) {
			chessWebService.move(movingPosition.getStart(), movingPosition.getEnd(), chessGame);
		}
		return model;
	}

	public Map<String, Object> setBoard() {
		Map<String, Object> model = new HashMap<>();

		model.put("board", chessGame.createBoardDto().getBoardDto());
		model.put("turn", chessGame.getTurn());
		model.put("score", chessGame.calculateScore());
		model.put("status", true);

		return model;
	}

	public Map<String, Object> move(String start, String end) {
		try {
			return chessWebService.move(start, end, chessGame);
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException | SQLException e) {
			Map<String, Object> model = new HashMap<>();

			model.put("status", false);
			model.put("exception", e.getMessage());
			model.put("destination", "chess.html");
			return model;
		}
	}

	public Map<String, Object> chooseFirstPosition(String position) {
		Map<String, Object> model = new HashMap<>();
		try {
			List<String> movablePositionNames = chessWebService.findMovablePositionNames(position, chessGame);

			model.put("movable", movablePositionNames);
			model.put("position", position);
			model.put("status", true);

			return model;
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
			model.put("status", false);
			model.put("exception", e.getMessage());
			return model;
		}
	}

	public Map<String, Object> chooseSecondPosition(String position) {
		Map<String, Object> model = new HashMap<>();
		try {
			model.put("status", true);
			model.put("position", position);
			return model;
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException e) {
			model.put("status", false);
			model.put("exception", e.getMessage());
			return model;
		}
	}
}
