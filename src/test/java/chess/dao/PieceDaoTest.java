package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Piece;
import chess.domain.position.Position;
import chess.web.dao.PieceDao;
import java.sql.Connection;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    PieceDao pieceDao = new PieceDao();

    @AfterEach
    void tearDown() {
        pieceDao.removeAll();
    }

    @Test
    void connection() {
        Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void savePieces() {
        Board board = Board.getInitializedInstance();
        Map<Position, Piece> value = board.getValue();
        pieceDao.savePieces(value);

        assertThat(pieceDao.findAll().size()).isEqualTo(32);
    }

}
