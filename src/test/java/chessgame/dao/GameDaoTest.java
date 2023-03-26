package chessgame.dao;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.state.Black;
import chessgame.domain.state.White;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();
    private final Connection connection = ConnectionGenerator.getConnection();
    private static final String gameName = "test";

    @BeforeEach
    void before() {
        gameDao.remove(gameName, connection);
    }

    @Test
    void save() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.save(board, gameName, new White(), connection));
    }

    @Test
    void readNull() {
        Assertions.assertThatNoException().isThrownBy(() -> assertThat(gameDao.read(gameName, connection)).isNull());
    }

    @Test
    void read() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.save(board, gameName, new White(), connection));
        Assertions.assertThatNoException().isThrownBy(() -> assertThat(gameDao.read(gameName, connection)).isNotNull());
    }

    @Test
    void remove() {
        Board board = new Board(ChessBoardFactory.create());

        gameDao.save(board, gameName, new White(), connection);

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.remove(gameName, connection));
    }

    @Nested
    @DisplayName("game의 turn을 찾는다.")
    class findTurnByGame {
        Board board = new Board(ChessBoardFactory.create());

        @Test
        void Should_FindWhite_WhenWhiteTurn() {
            gameDao.save(board, gameName, new White(), connection);

            Assertions.assertThat(gameDao.findTurnByGame(gameName, connection)).isEqualTo("White");
        }

        @Test
        void Should_FindBlack_WhenBlackTurn() {
            gameDao.save(board, gameName, new Black(), connection);

            Assertions.assertThat(gameDao.findTurnByGame(gameName, connection)).isEqualTo("Black");
        }
    }
}