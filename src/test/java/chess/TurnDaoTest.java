package chess;

import chess.model.dao.TurnDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnDaoTest {
    @Test
    @DisplayName("턴이 초기에 저장되었는지 확인한다")
    void init() {
        TurnDao turnDao = new TurnDao();
        turnDao.init();

        String turn = turnDao.findOne();

        assertThat(turn).isEqualToIgnoringCase("white");
    }

    @Test
    @DisplayName("턴이 존재하지 않는 경우 무엇을 반환하는지 확인")
    void getTurn() {
        TurnDao turnDao = new TurnDao();

        String turn = turnDao.findOne();

        assertThat(turn).isEqualToIgnoringCase("");
    }

    @Test
    @DisplayName("턴이 update 되는지 확인한다")
    void update() {
        TurnDao turnDao = new TurnDao();
        turnDao.init();

        turnDao.update("BLACK");
        String turn = turnDao.findOne();

        assertThat(turn).isEqualToIgnoringCase("black");
    }

    @Test
    @DisplayName("저장된 턴을 모두 삭제한다.")
    void deleteAll() {
        TurnDao turnDao = new TurnDao();
        turnDao.init();
        turnDao.deleteAll();

        assertThat(turnDao.findOne()).isEqualToIgnoringCase("");
    }
}
