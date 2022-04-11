package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Piece;
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
    void saveAll() {
        final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        final List<Piece> pieces = chessmenInitializer.init().getPieces();

        gameDao.saveById(gameId);

        assertThatCode(() ->
            pieceDao.saveAllByGameId(pieces, gameId))
            .doesNotThrowAnyException();
    }

    @Test
    void deleteAllByGameId() {
        gameDao.saveById(gameId);
        pieceDao.deleteAllByGameId(gameId);

        final List<Piece> pieces = pieceDao.findAllByGameId(gameId).getPieces();

        assertThat(pieces.size()).isEqualTo(0);
    }

}
