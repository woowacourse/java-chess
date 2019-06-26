package chess.dao;

import org.junit.jupiter.api.BeforeEach;

public class ResultDaoTest {

    ResultDao resultDao;

    @BeforeEach
    void setUp() {
        resultDao = ResultDao.getInstance();
    }
}
