package chess.domain.dao;

import chess.domain.dto.GameDto;
import chess.domain.game.board.ChessBoardFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class GameDaoTest {

    private Connection connection;
    private GameDao gameDao;
    private Connector connector = new Connector();

    @BeforeEach
    void set() throws SQLException {
        connection = connector.makeConnection(Connector.DEV_DB_URL);
        gameDao = new GameDao(connection, connector);
        connection.setAutoCommit(false);
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
        int actual = gameDao.findLastGameId();
        //then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("id로 게임을 불러온다")
    void findById() {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        //when
        GameDto actual = gameDao.findById(1);
        //then
        assertThat(actual.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("가장 최근 게임을 삭제한다.")
    void delete() {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        gameDao.save(ChessBoardFactory.initBoard());
        //when
        gameDao.delete();
        //then
        assertThat(gameDao.findLastGameId()).isEqualTo(1);
    }

    @AfterEach
    void end() throws SQLException {
        connection.rollback();
        connection.close();
    }

}
