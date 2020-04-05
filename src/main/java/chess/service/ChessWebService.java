package chess.service;

import chess.domain.web.Log;
import chess.domain.web.LogDao;

import java.sql.SQLException;
import java.util.Map;

public class ChessWebService {
	public void clearLog() throws SQLException {
		LogDao logDao = new LogDao();
		logDao.clear();
	}

	public Map<Integer, Log> selectAllLog() throws SQLException {
		LogDao logDao = new LogDao();
		return logDao.selectAll();
	}

	public void insertLog(String start, String end) throws SQLException {
		LogDao logDao = new LogDao();
		logDao.insert(start, end);
	}
}
