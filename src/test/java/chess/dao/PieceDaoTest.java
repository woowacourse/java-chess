package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    @Test
    void connection() {
        final PieceDao pieceDao = new PieceDao();
        final Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final PieceDao pieceDao = new PieceDao();
        assertThatCode(() ->
            pieceDao.save(new Pawn(Color.BLACK, Position.of("a7"))))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition() {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")));

        assertThatCode(() ->
            pieceDao.deleteByPosition("a7"))
            .doesNotThrowAnyException();
    }

    @Test
    void updateByPosition() {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")));
        pieceDao.updateByPosition("a7", "a6");
    }

    @Test
    void findAll() {
        final PieceDao pieceDao = new PieceDao();

        final List<Piece> pieces = pieceDao.findAll();

        assertThat(pieces.size()).isNotEqualTo(0);
    }

    @Test
    void findByPosition() {
        final PieceDao pieceDao = new PieceDao();


        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")));

        final Piece piece = pieceDao.findByPosition("a7");

        assertThat(piece.getPosition().getPosition()).isEqualTo("a7");
    }

}
