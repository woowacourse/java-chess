package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLDataException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RoundInfoDaoTest {
    @Test
    void selectAllUnfinishedGame() throws SQLDataException {
        System.out.println(RoundInfoDao.getInstance().selectAllGame(false));
    }

    @Test
    void insertRoundInfo() throws SQLDataException {
        assertThat(RoundInfoDao.getInstance().insertRoundInfo("김고래", "whale"))
                .isNotNull();
    }

    @Test
    void updateGameOver() throws SQLDataException {
        assertThat(RoundInfoDao.getInstance().updateGameOver(1)).isEqualTo(1);
    }

    @Test
    void selectRoundInfo() throws SQLDataException {
        System.out.println(RoundInfoDao.getInstance().selectRoundInfo(1));
    }
}
