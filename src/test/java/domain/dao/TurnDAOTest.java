package domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import dao.TurnDAO;
import domain.team.Team;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnDAOTest {
    TurnDAO turnDAO;

    @BeforeEach
    void init() {
        turnDAO = TurnDAO.getInstance();
    }

    @Test
    @DisplayName("시작할 때 턴이 WHITE인지")
    void start() throws SQLException {
        turnDAO.start();
        assertThat(turnDAO.getTurn()).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("턴이 바뀌는 지")
    void changeTurn() throws SQLException {
        turnDAO.start();
        turnDAO.changeTurn();
        assertThat(turnDAO.getTurn()).isEqualTo(Team.BLACK);
    }

    @AfterEach
    void reset() throws SQLException {
        turnDAO.delete();
    }
}
