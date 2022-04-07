package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoTest {

    @DisplayName("데이터가 저장되는지 확인한다.")
    @Test()
    void save() {
        TurnDao turnDao = new TurnDao();
        turnDao.delete();
        turnDao.save("white");

        assertThat(turnDao.find()).isEqualTo("white");
    }

    @DisplayName("데이터가 삭제되는지 확인한다.")
    @Test()
    void delete() {
        TurnDao turnDao = new TurnDao();
        turnDao.delete();
        turnDao.save("white");
        turnDao.delete();

        assertThatThrownBy(turnDao::find)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 일치하는 데이터가 없습니다.");
    }

    @DisplayName("데이터가 최신화 되는지 확인한다.")
    @Test()
    void update() {
        TurnDao turnDao = new TurnDao();
        turnDao.delete();
        turnDao.save("white");
        turnDao.update("white", "black");

        assertThat(turnDao.find()).isEqualTo("black");
    }
}
