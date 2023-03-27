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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameDaoTest {
    private final GameDao gameDao = new GameDao();
    private static final String gameName = "test";

    @BeforeEach
    void before() {
        gameDao.remove(gameName);
    }

    @Test
    @DisplayName("DB에 값이 잘 저장되는지 확인한다.")
    void save() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.save(board, gameName, new White()));
    }

    @Test
    @DisplayName("DB에 읽어오는 값이 존재하지 않을 경우를 확인한다.")
    void readNull() {
        Assertions.assertThatNoException().isThrownBy(() -> assertThat(gameDao.read(gameName)).isNull());
    }

    @Test
    @DisplayName("DB에 읽어오는 값이 존재 할 경우를 확인한다.")
    void read() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.save(board, gameName, new White()));
        Assertions.assertThatNoException().isThrownBy(() -> assertThat(gameDao.read(gameName)).isNotNull());
    }

    @Test
    @DisplayName("DB에 있는 특정 game과 board 값을 삭제한다.")
    void remove() {
        Board board = new Board(ChessBoardFactory.create());

        gameDao.save(board, gameName, new White());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.remove(gameName));
    }

    @Nested
    @DisplayName("특정 game의 현재 turn을 찾는다.")
    class findTurnByGame {
        Board board = new Board(ChessBoardFactory.create());

        @Test
        @DisplayName("White팀 턴일 경우 turn이 잘 저장되었는지 확인한다.")
        void Should_FindWhite_WhenWhiteTurn() {
            gameDao.save(board, gameName, new White());

            Assertions.assertThat(gameDao.findTurnByGame(gameName)).isEqualTo("White");
        }

        @Test
        @DisplayName("Black팀 턴일 경우 turn이 잘 저장되었는지 확인한다.")
        void Should_FindBlack_WhenBlackTurn() {
            gameDao.save(board, gameName, new Black());

            Assertions.assertThat(gameDao.findTurnByGame(gameName)).isEqualTo("Black");
        }
    }
}