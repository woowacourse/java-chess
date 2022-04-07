package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.TurnDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameDaoTest {

    private Connection connection;

    @BeforeEach
    void beforeEach() {
        connection = JdbcTemplateUtil.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Order(1)
    @Test
    void getTurn() {
        GameDaoImpl gameDao = new GameDaoImpl();
        TurnDto turnDto = gameDao.getTurn();
        assertThat(turnDto.getTurn()).isEqualTo("WHITE");
    }

    @Order(2)
    @Test
    void changeTurn() {
        GameDaoImpl gameDao = new GameDaoImpl();
        TurnDto beforeTurnDto = gameDao.getTurn();

        gameDao.changeTurn();

        TurnDto afterTurnDto = gameDao.getTurn();

        assertThat(beforeTurnDto.getTurn()).isNotEqualTo(afterTurnDto.getTurn());
    }

    @AfterAll
    static void afterAll() {
        GameDaoImpl gameDao = new GameDaoImpl();
        gameDao.changeTurn();
    }
}
