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
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        assertThatCode(() ->
            pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), id))
            .doesNotThrowAnyException();
    }

    @Test
    void saveAll() {
        final PieceDao pieceDao = new PieceDao();

        final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        final List<Piece> pieces = chessmenInitializer.init().getPieces();

        final GameDao gameDao = new GameDao();
        String id = gameDao.create();

        assertThatCode(() ->
            pieceDao.saveAll(pieces, id))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), id);

        assertThatCode(() ->
            pieceDao.deleteByPosition("a7", id))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition_if_not_exist() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        assertThatCode(() ->
            pieceDao.deleteByPosition("d5", id))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteAll() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        pieceDao.deleteAll(id);

        final List<Piece> pieces = pieceDao.findAll(id).getPieces();

        assertThat(pieces.size()).isEqualTo(0);
    }

    @Test
    void updateByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), id);

        pieceDao.updateByPosition("a7", "a6", id);

        assertThat(pieceDao.findByPosition("a6", id).getName()).isEqualTo("pawn");
    }

    @Test
    void findAll() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), id);

        final List<Piece> pieces = pieceDao.findAll(id).getPieces();

        assertThat(pieces.size()).isNotEqualTo(0);
    }

    @Test
    void findByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String id = gameDao.create();

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), id);

        final Piece piece = pieceDao.findByPosition("a7", id);

        assertThat(piece.getPosition().getPosition()).isEqualTo("a7");
    }

}
