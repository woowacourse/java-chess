package chess.service;

import java.sql.SQLException;
import java.util.Optional;

import chess.domain.chess.ChessDAO;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
    }

    public void insert() throws SQLException {
        chessDAO.insert();
    }

    public void updateTurn(Long chessId) throws SQLException {
        chessDAO.updateTurn(chessId);
    }

    public void delete(Long chessId) throws SQLException {
        chessDAO.delete(chessId);
    }

    public Long findChessId() throws SQLException {
        return chessDAO.findChessId()
                       .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
    }

    public String findChessIdAsString() throws SQLException {
        final Optional<Long> chessId = chessDAO.findChessId();
        if (chessId.isPresent()) {
            return String.valueOf(chessId.get());
        }
        return "EMPTY";
    }

    public String findTurnById(Long chessId) throws SQLException {
        return chessDAO.findTurnById(chessId)
                       .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
    }
}
