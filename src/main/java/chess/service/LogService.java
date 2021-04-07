package chess.service;

import chess.dao.LogDAO;
import chess.domain.ChessGame;

import java.util.List;

public final class LogService {
    private final LogDAO logDAO;

    public LogService(final LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    public void initializeByRoomId(final String roomId) {
        logDAO.deleteLogByRoomId(roomId);
    }

    public List<String[]> logByRoomId(final String roomId) {
        return logDAO.allLogByRoomId(roomId);
    }

    public void executeLog(final List<String[]> logs, final ChessGame chessGame) {
        logs.forEach(positions -> chessGame.move(positions[0], positions[1]));
    }

    public void createLog(final String roomId, final String startPoint, final String endPoint) {
        logDAO.createLog(roomId, startPoint, endPoint);
    }
}
