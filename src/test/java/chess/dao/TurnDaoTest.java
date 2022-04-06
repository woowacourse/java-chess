package chess.dao;

import org.junit.jupiter.api.Test;

public class TurnDaoTest {

    @Test
    void saveTurn() {
        final TurnDao turnDao = new TurnDao();
        turnDao.saveTurn("black");
    }


    @Test
    void getTurn() {
        final TurnDao turnDao = new TurnDao();
        turnDao.getTurnTeam();
        System.out.println(turnDao.getTurnTeam());
    }
}
