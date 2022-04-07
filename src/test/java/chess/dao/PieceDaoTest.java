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
        final String gameId = "123";
        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void saveAll() {
        final PieceDao pieceDao = new PieceDao();

        final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        final List<Piece> pieces = chessmenInitializer.init().getPieces();

        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.saveAll(pieces, gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        assertThatCode(() ->
            pieceDao.deleteByPosition("a7", gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition_if_not_exist() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.deleteByPosition("d5", gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteAll() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);


        pieceDao.deleteAll(gameId);

        final List<Piece> pieces = pieceDao.findAll(gameId).getPieces();

        assertThat(pieces.size()).isEqualTo(0);
    }

    @Test
    void updateByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        pieceDao.updateByPosition("a7", "a6", gameId);

        assertThat(pieceDao.findByPosition("a6", gameId).getName()).isEqualTo("pawn");
    }

    @Test
    void findAll() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        final List<Piece> pieces = pieceDao.findAll(gameId).getPieces();

        assertThat(pieces.size()).isNotEqualTo(0);
    }

    @Test
    void findByPosition() {
        final PieceDao pieceDao = new PieceDao();
        final GameDao gameDao = new GameDao();
        final String gameId = "123";
        gameDao.create(gameId);

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        final Piece piece = pieceDao.findByPosition("a7", gameId);

        assertThat(piece.getPosition().getPosition()).isEqualTo("a7");
    }

}
