package chess.repository;

import chess.dao.ChessDAO;
import chess.dao.ChessDAO.Fake;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessRepositoryTest {

    private ChessDAO chessDAO;
    private ChessRepository chessRepository;

    @BeforeEach
    void init() {
        chessDAO = new Fake();
        chessRepository = new ChessRepositoryImpl(chessDAO);
    }

    @Test
    void createGame() {
        ChessGame game = chessRepository.createGame(2L);
        Assertions.assertThat(game).isNotNull();
        Assertions.assertThat(game.pieces().asList()).hasSizeGreaterThanOrEqualTo(0);
    }

    @Test
    void endGame() {
        long gameId = 2L;
        chessRepository.createGame(gameId);
        chessRepository.endGame(gameId);
        List<Piece> pieces = chessDAO.loadGame(gameId);
        Assertions.assertThat(pieces).isEmpty();
    }

    @Test
    void restart() {
        long gameId = 1L;
        ChessGame game = chessRepository.createGame(gameId);
        chessRepository.restart(gameId);
        ChessGame secondGame = chessRepository.createGame(gameId);

        Assertions.assertThat(game).isNotEqualTo(secondGame);
    }

    @Test
    void save() {
        long gameId = 1L;
        ChessGame game = chessRepository.createGame(gameId);
        chessRepository.save(gameId);

        ChessGame secondGame = chessRepository.createGame(gameId);
        Assertions.assertThat(game).isEqualTo(secondGame);
    }
}