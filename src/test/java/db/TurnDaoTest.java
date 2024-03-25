package db;

import domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnDaoTest {
    @Test
    void 턴을_저장한다() {
        TurnDao turnDao = new TurnDao();

        turnDao.update(Color.WHITE.name());

        assertThat(turnDao.find()).isEqualTo("WHITE");
    }
}
