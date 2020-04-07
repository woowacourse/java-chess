package chess.service;

import chess.domain.web.HistoryDao;
import chess.domain.web.MovingPosition;

import java.sql.SQLException;
import java.util.List;

public class ChessWebService {
	public void clearHistory() throws SQLException {
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
}
