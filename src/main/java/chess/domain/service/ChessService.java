package chess.domain.service;

import java.sql.SQLException;

import chess.domain.board.BoardDTO;
import chess.domain.chess.ChessDAO;
import chess.domain.chess.ChessDTO;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService() {
        this.chessDAO = new ChessDAO();
    }

    public Long findChessId() throws SQLException {
        return chessDAO.findChessId()
                       .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
    }

    public ChessDTO makeChess(BoardDTO boardDTO) throws SQLException {
        String lastTurn = chessDAO.findTurnByChessId();
        return new ChessDTO(lastTurn, boardDTO);
    }
}
