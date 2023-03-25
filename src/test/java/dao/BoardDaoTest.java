package dao;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;
import chessgame.domain.state.Black;
import chessgame.domain.state.State;
import chessgame.domain.state.White;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardDaoTest {
    private final BoardDao boardDao = new BoardDao();

    @Test
    public void connection() {
        assertThat(boardDao.getConnection()).isNotNull();
    }

    @Test
    public void save() throws SQLException{
        Board board = new Board(ChessBoardFactory.create());
        Assertions.assertThatNoException().isThrownBy(()->boardDao.save(board,"test", new White()));
    }

    @Test
    public void read(){
        Assertions.assertThatNoException().isThrownBy(()->assertThat(boardDao.read("test")).isNull());

        Board board = new Board(ChessBoardFactory.create());

        Assertions.assertThatNoException().isThrownBy(()->boardDao.save(board,"test",new White()));
    }

    @Test
    public void remove(){
        Board board = new Board(ChessBoardFactory.create());
        boardDao.save(board,"test", new White());
        Assertions.assertThatNoException().isThrownBy(()->boardDao.remove("test"));
    }
}