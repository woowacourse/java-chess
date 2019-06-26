package chess.dao;

import chess.db.DBManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class TurnDaoTest {
    private Connection connection;
    private TurnDao turnDao;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DBManager.getConnection();
        connection.setAutoCommit(false);
        turnDao = new TurnDao(connection);
    }
    @Test
    void 첫번째_turn_추가() throws SQLException{
        turnDao.addFirstTurn(1);
    }

    @Test
    void 현재_차례_팀_조회() throws SQLException {
        turnDao.addFirstTurn(2);
        assertThat(turnDao.selectCurrentTurn(2)).isEqualTo("WHITE");
    }

    @Test
    void 차례_디비에_수정() throws SQLException{
        turnDao.addFirstTurn(3);
        turnDao.updateCurrentTurn(3);
        assertThat(turnDao.selectCurrentTurn(3)).isEqualTo("BLACK");
    }

    @AfterEach
    void tearDown() throws SQLException{
        connection.rollback();
    }
}
