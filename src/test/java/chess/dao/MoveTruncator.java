package chess.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class MoveTruncator {

    private static MoveDao moveDao = new MoveDaoImpl();

    @AfterAll
    static void tearDown() {
        moveDao.truncateMove();
    }

    @BeforeEach
    void setUp() {
        moveDao.truncateMove();
    }
}
