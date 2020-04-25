package chess.service;

import chess.database.dao.ChessBoardDao;
import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.sql.SQLException;
import java.util.Map;

public class ChessBoardService {
    private static ChessBoardService INSTANCE = new ChessBoardService();
    private static ChessBoardDao chessBoardDao = ChessBoardDao.getInstance();

    public static ChessBoardService getInstance() {
        if (INSTANCE == null) return new ChessBoardService();
        return INSTANCE;
    }

    private ChessBoardService() {
    }

    public void initialize() throws SQLException {
        saveBoard(new ChessBoard());
    }

    public void saveBoard(ChessBoard chessBoard) throws SQLException {
        chessBoardDao.delete();
        Map<Square, Piece> board = chessBoard.getChessBoard();
        for (Square square : board.keySet()) {
            chessBoardDao.save(square, board.get(square));
        }
    }

    public ChessBoard load() throws SQLException {
        return chessBoardDao.load();
    }
}
