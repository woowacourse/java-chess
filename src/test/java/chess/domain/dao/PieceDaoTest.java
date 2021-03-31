package chess.domain.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao pieceDao;

    @BeforeEach
    void setup() {
        pieceDao = new PieceDao();
    }

    @Test
    void save() throws SQLException {
        final Map<Position, Piece> chessBoard = new Board().unwrap();
        pieceDao.save(chessBoard);
    }

    @Test
    void deleteAll() throws SQLException {
        pieceDao.deleteAll();
    }

    @Test
    void load() throws SQLException {
        final Map<Position, Piece> chessBoard = pieceDao.load();
    }
}