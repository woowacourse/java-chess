package dao;

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

class BoardDaoTest {
    private final BoardDao boardDao = new BoardDao();
    private static final String gameName = "test";
    @BeforeEach
    void delete(){
        boardDao.remove(gameName);
    }
    @Test
    void connection() {
        assertThat(boardDao.getConnection()).isNotNull();
    }

    @Test
    void save() {
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(() -> boardDao.save(board, gameName, new White()));
    }

    @Test
    void readNull(){
        Assertions.assertThatNoException().isThrownBy(()->assertThat(boardDao.read(gameName)).isNull());
    }
    @Test
    void read(){
        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(()->boardDao.save(board,gameName,new White()));
        Assertions.assertThatNoException().isThrownBy(()->assertThat(boardDao.read(gameName)).isNotNull());
    }

    @Test
    void remove(){
        Board board = new Board(ChessBoardFactory.create());

        boardDao.save(board,gameName, new White());

        Assertions.assertThatNoException().isThrownBy(()->boardDao.remove(gameName));
    }

    @Nested
    @DisplayName("game의 turn을 찾는다.")
    class findTurnByGame {
        Board board = new Board(ChessBoardFactory.create());

        @Test
        void Should_FindWhite_WhenWhiteTurn() {
            boardDao.save(board, gameName, new White());

            Assertions.assertThat(boardDao.findTurnByGame(gameName)).isEqualTo("White");
        }

        @Test
        void Should_FindBlack_WhenBlackTurn() {
            boardDao.save(board, gameName, new Black());

            Assertions.assertThat(boardDao.findTurnByGame(gameName)).isEqualTo("Black");
        }
    }
}