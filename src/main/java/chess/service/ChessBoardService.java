package chess.service;

import chess.database.dao.ChessBoardDao;
import chess.domain.ChessBoard;
import chess.domain.square.Square;

import java.sql.SQLException;

public class ChessBoardService {
    private ChessBoardDao chessBoardDao;

    public ChessBoardService(ChessBoardDao chessBoardDao) {
        this.chessBoardDao = chessBoardDao;
    }

    public void save(ChessBoard board) throws SQLException {
        chessBoardDao.delete();
        for (Square square : board.getChessBoard().keySet()) {
            chessBoardDao.save(square, board.getChessBoard().get(square));
        }
    }

}
