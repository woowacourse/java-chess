package chess.service;

import chess.dao.LogDAO;
import chess.domain.ChessGame;

import java.sql.SQLException;
import java.util.List;

public class LogService {
    private final LogDAO logDAO;

    public LogService(final LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    public void initializeByRoomId(final String roomId) throws SQLException {
        logDAO.deleteLogByRoomId(roomId);
    }

    public List<String[]> logByRoomId(final String roomId) throws SQLException {
        return logDAO.allLogByRoomId(roomId);
    }

    public void executeLog(final List<String[]> logs, final ChessGame chessGame) {
        logs.forEach(positions -> chessGame.move(positions[0], positions[1]));
    }

    public void createLog(final String roomId, final String startPoint, final String endPoint) throws SQLException {
        logDAO.createLog(roomId, startPoint, endPoint);
    }
}
