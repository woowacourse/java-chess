package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    private TurnDao turnDao;

    @BeforeEach
    void setUp() {
        TestDataSource datasource = new TestDataSource();
        turnDao = new TurnDaoImpl(datasource);
    }

    @Test
    @DisplayName("현재 턴을 가져온다.")
    void getTurn() {
        assertThat(turnDao.getCurrentTurn()).isEqualTo("white");
    }

    @Test
    @DisplayName("턴을 변경하고 가져온다.")
    void updateAndGetCurrentTurn() {
        turnDao.updateTurn("black", "white");
        assertThat(turnDao.getCurrentTurn()).isEqualTo("black");
    }

    @AfterEach
    void teardown() {
        turnDao.updateTurn("white", "black");
    }
}
