package chess.domain.dao;

import chess.domain.dto.GameDto;
import chess.domain.game.board.ChessBoardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameDaoTest {

    private Connection connection;
    private GameDao gameDao;

    @BeforeEach
    void set() throws SQLException {
        connection = Connector.getConnection();
        gameDao = new GameDao(connection);
        connection.setAutoCommit(false);
    }

    @Test
    void connection(){
        assertThat(Connector.getConnection()).isNotNull();
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void save() {
        int actual = gameDao.save(ChessBoardFactory.initBoard());
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("가장 최근 게임을 불러온다")
    void findLastGame() throws SQLException {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        gameDao.save(ChessBoardFactory.initBoard());
        //when
        int actual = gameDao.findLastGame();
        //then
        assertThat(actual).isEqualTo(2);
    }


    @Test
    @DisplayName("id로 게임을 불러온다")
    void findById(){
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        //when
        GameDto actual = gameDao.findById(1);
        //then
        assertThat(actual.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("가장 최근 게임을 삭제한다.")
    void delete() throws SQLException {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        gameDao.save(ChessBoardFactory.initBoard());
        //when
        gameDao.delete();
        //then
        assertThat(gameDao.findLastGame()).isEqualTo(1);
    }

    @AfterEach
    void end() throws SQLException {
        connection.rollback();
    }

}