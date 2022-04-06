package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    @Test
    void save() {
        final PieceDao pieceDao = new PieceDao();
        assertThatCode(() ->
            pieceDao.save(new Pawn(Color.BLACK, Position.of("a7"))))
            .doesNotThrowAnyException();
    }

    @Test
    void saveAll() {
        final PieceDao pieceDao = new PieceDao();

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        final List<Piece> pieces = chessmenInitializer.init().getPieces();
        assertThatCode(() ->
            pieceDao.saveAll(pieces))
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
    void deleteByPosition_if_not_exist() {
        final PieceDao pieceDao = new PieceDao();

        assertThatCode(() ->
            pieceDao.deleteByPosition("d5"))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteAll() {
        final PieceDao pieceDao = new PieceDao();

        pieceDao.deleteAll();

        final List<Piece> pieces = pieceDao.findAll().getPieces();

        assertThat(pieces.size()).isEqualTo(0);
    }

    @Test
    void updateByPosition() {
        final PieceDao pieceDao = new PieceDao();

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")));

        pieceDao.updateByPosition("a7", "a6");

        assertThat(pieceDao.findByPosition("a6").getName()).isEqualTo("pawn");
    }

    @Test
    void findAll() {
        final PieceDao pieceDao = new PieceDao();

        final List<Piece> pieces = pieceDao.findAll().getPieces();

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
