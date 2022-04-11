package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class TurnDaoTest {

    @Test
    void saveTurn() {
        final TurnDao turnDao = new TurnDao();
        turnDao.saveTurn("black");
        assertThat(turnDao.getTurnTeam()).isEqualTo(Team.BLACK);
    }
}
