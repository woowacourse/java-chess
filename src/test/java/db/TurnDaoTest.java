package db;

import domain.dto.TurnDto;
import domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnDaoTest {
    @Test
    void 턴을_저장한다() {
        TurnDto whiteTurn = new TurnDto("WHITE");
        TurnDao turnDao = new TurnDao();

        turnDao.update(whiteTurn);

        assertThat(turnDao.find().getTurn()).isEqualTo(Color.WHITE);
    }
}
