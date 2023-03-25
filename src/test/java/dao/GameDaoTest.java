package dao;

import chessgame.dao.GameDao;
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
    void delete(){
        gameDao.remove(gameName);
    }

    @Test
    void save() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> gameDao.save(board, gameName, new White()));
    }

    @Test
    void readNull(){
        Assertions.assertThatNoException().isThrownBy(()->assertThat(gameDao.read(gameName)).isNull());
    }
    @Test
    void read(){
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(()-> gameDao.save(board,gameName,new White()));
        Assertions.assertThatNoException().isThrownBy(()->assertThat(gameDao.read(gameName)).isNotNull());
    }

    @Test
    void remove(){
        Board board = new Board(ChessBoardFactory.create());

        gameDao.save(board,gameName, new White());

        Assertions.assertThatNoException().isThrownBy(()-> gameDao.remove(gameName));
    }

    @Nested
    @DisplayName("game의 turn을 찾는다.")
    class findTurnByGame {
        Board board = new Board(ChessBoardFactory.create());

        @Test
        void Should_FindWhite_WhenWhiteTurn() {
            gameDao.save(board, gameName, new White());

            Assertions.assertThat(gameDao.findTurnByGame(gameName)).isEqualTo("White");
        }

        @Test
        void Should_FindBlack_WhenBlackTurn() {
            gameDao.save(board, gameName, new Black());

            Assertions.assertThat(gameDao.findTurnByGame(gameName)).isEqualTo("Black");
        }
    }
}