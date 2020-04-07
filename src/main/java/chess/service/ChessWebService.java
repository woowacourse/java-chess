package chess.service;

import chess.domain.game.ChessGame;
import chess.domain.position.PositionFactory;
import chess.domain.web.HistoryDao;
import chess.domain.web.MovingPosition;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessWebService {
	public Map<String, Object> index(ChessGame chessGame) {
		Map<String, Object> model = new HashMap<>();

		model.put("status", true);
		chessGame.reset();

		return model;
	}

	public Map<String, Object> startGame(ChessGame chessGame) throws SQLException {
		Map<String, Object> model = new HashMap<>();

		model.put("status", true);
		chessGame.reset();
		clearHistory();

		return model;
	}

	public Map<String, Object> loadGame(ChessGame chessGame) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		model.put("status", true);

		List<MovingPosition> histories = selectAllHistory();

		for (MovingPosition movingPosition : histories) {
			move(movingPosition.getStart(), movingPosition.getEnd(), chessGame);
		}
		return model;
	}

	public Map<String, Object> setBoard(ChessGame chessGame) {
		Map<String, Object> model = new HashMap<>();

		model.put("board", chessGame.createBoardDto().getBoardDto());
		model.put("turn", chessGame.getTurn());
		model.put("score", chessGame.calculateScore());
		model.put("status", true);

		return model;
	}

	public Map<String, Object> move(String start, String end, ChessGame chessGame) {
		try {
			return updateMoving(start, end, chessGame);
		} catch (IllegalArgumentException | UnsupportedOperationException | NullPointerException | SQLException e) {
			Map<String, Object> model = new HashMap<>();

			model.put("status", false);
			model.put("exception", e.getMessage());
			model.put("destination", "chess.html");
			return model;
		}
	}

	public Map<String, Object> chooseFirstPosition(String position, ChessGame chessGame) {
		Map<String, Object> model = new HashMap<>();
		try {
			List<String> movablePositionNames = chessGame.findMovablePositionNames(position);

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

	private void clearHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.clear();
	}

	private List<MovingPosition> selectAllHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		return historyDao.selectAll();
	}

	private void insertHistory(String start, String end) throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.insert(start, end);
	}

	private Map<String, Object> updateMoving(String start, String end, ChessGame chessGame) throws SQLException {
		Map<String, Object> model = new HashMap<>();

		if (start.equals(end)) {
			model.put("status", true);
			model.put("destination", "chess.html");
			return model;
		}

		chessGame.move(PositionFactory.of(start), PositionFactory.of(end));
		insertHistory(start, end);
		model.put("status", true);

		if (chessGame.isKingDead()) {
			model.put("winner", chessGame.getAliveKingColor());
			chessGame.reset();
			clearHistory();
			model.put("destination", "result.html");
			return model;
		}
		model.put("destination", "chess.html");
		return model;
	}
}
