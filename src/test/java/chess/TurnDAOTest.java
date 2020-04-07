package chess;

import chess.DAO.TurnDAO;
import chess.piece.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnDAOTest {
    private TurnDAO turnDAO;
    private Team savedTurn;

    @BeforeEach
    public void setUp() throws SQLException {
        turnDAO = new TurnDAO(DBConnector.getConnection());
        savedTurn = turnDAO.loadTurn();
    }

    @Test
    void saveTurnTest() throws SQLException {
        turnDAO.saveTurn(Team.WHITE);
        assertThat(turnDAO.loadTurn()).isEqualTo(Team.WHITE);
        turnDAO.saveTurn(Team.BLACK);
        assertThat(turnDAO.loadTurn()).isEqualTo(Team.BLACK);
    }

    @AfterEach
    void tearDown() throws SQLException {
        turnDAO.saveTurn(savedTurn);
    }
}
