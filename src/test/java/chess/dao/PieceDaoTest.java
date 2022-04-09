package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {

    final PieceDao pieceDao = new PieceDao();
    final GameDao gameDao = new GameDao();

    static int id = 1000;
    String gameId = "999";

    @BeforeEach
    void setup_chessGame() {
        gameId = String.valueOf(++id);
    }

    @Test
    void save() {
        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void saveAll() {
        final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        final List<Piece> pieces = chessmenInitializer.init().getPieces();

        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.saveAll(pieces, gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition() {
        gameDao.create(gameId);

        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        assertThatCode(() ->
            pieceDao.deleteByPosition("a7", gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteByPosition_if_not_exist() {
        gameDao.create(gameId);

        assertThatCode(() ->
            pieceDao.deleteByPosition("d5", gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteAllByGameId() {
        gameDao.create(gameId);
        pieceDao.deleteAllByGameId(gameId);

        final List<Piece> pieces = pieceDao.findAllByGameId(gameId).getPieces();

        assertThat(pieces.size()).isEqualTo(0);
    }

    @Test
    void updateByPosition() {
        gameDao.create(gameId);
        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        pieceDao.updateByPosition("a7", "a6", gameId);

        assertThat(pieceDao.findByPosition("a6", gameId).getName()).isEqualTo("pawn");
    }

    @Test
    void findAllByGameId() {
        gameDao.create(gameId);
        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        final List<Piece> pieces = pieceDao.findAllByGameId(gameId).getPieces();

        assertThat(pieces.size()).isNotEqualTo(0);
    }

    @Test
    void findByPosition() {
        gameDao.create(gameId);
        pieceDao.save(new Pawn(Color.BLACK, Position.of("a7")), gameId);

        final Piece piece = pieceDao.findByPosition("a7", gameId);

        assertThat(piece.getPosition().getPosition()).isEqualTo("a7");
    }

}
