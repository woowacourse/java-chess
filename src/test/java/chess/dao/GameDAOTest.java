package chess.dao;

import org.junit.jupiter.api.BeforeEach;

class GameDAOTest {
    private GameDAO gameDao;

    @BeforeEach
    public void setup() {
        gameDao = new GameDAO();
    }
}