package chess.persistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameDAOTest {

    private GameDAO gameDAO;

    @BeforeEach
    void setUp() {
        this.gameDAO = GameDAO.getInstance();
    }

    @Test
    void addGameTest() {
        int numOfGamesBeforeAdd = gameDAO.countGames();
        gameDAO.addGame("New Game");
        int numOfGamesAfterAdd = gameDAO.countGames();
        assertThat(numOfGamesAfterAdd).isEqualTo(numOfGamesBeforeAdd + 1);
        gameDAO.removeGame(gameDAO.getGameId("New Game"));
    }
}
