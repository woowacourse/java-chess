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
	public void resetGame(ChessGame chessGame) {
		chessGame.reset();
	}

	public void resetGameAndHistory(ChessGame chessGame) throws SQLException {
		chessGame.reset();
		clearHistory();
	}

	private void clearHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.clear();
	}

	public List<MovingPosition> selectAllHistory() throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		return historyDao.selectAll();
	}

	public void insertHistory(String start, String end) throws SQLException {
		HistoryDao historyDao = new HistoryDao();
		historyDao.insert(start, end);
	}

	public List<String> findMovablePositionNames(String position, ChessGame chessGame) {
		return chessGame.findMovablePositionNames(position);
	}

	public Map<String, Object> move(String start, String end, ChessGame chessGame) throws SQLException {
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
			resetGameAndHistory(chessGame);
			model.put("destination", "result.html");
			return model;
		}
		model.put("destination", "chess.html");
		return model;
	}
}
