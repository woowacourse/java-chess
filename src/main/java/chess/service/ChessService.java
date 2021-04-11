package chess.service;

import java.util.Optional;

import chess.domain.chess.ChessDAO;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
    }

    public Long insert() {
        chessDAO.insert();
        return findChessId();
    }

    public void updateTurn(Long chessId) {
        chessDAO.updateTurn(chessId);
    }

    public void delete(Long chessId) {
        chessDAO.delete(chessId);
    }

    public Long findChessId() {
        return chessDAO.findChessId()
                       .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
    }

    public String findChessIdAsString() {
        final Optional<Long> chessId = chessDAO.findChessId();
        if (chessId.isPresent()) {
            return String.valueOf(chessId.get());
        }
        return "EMPTY";
    }

    public String findTurnById(Long chessId) {
        return chessDAO.findTurnById(chessId)
                       .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
    }
}
