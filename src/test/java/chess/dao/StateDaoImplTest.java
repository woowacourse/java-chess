package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateDaoImplTest {

    @DisplayName("데이터가 저장되는지 확인한다.")
    @Test()
    void save() {
        StateDao stateDao = new StateDaoImpl();
        stateDao.save("BLACK_TURN");

        assertThat(stateDao.find()).isEqualTo("BLACK_TURN");
    }

    @DisplayName("데이터가 삭제되는지 확인한다.")
    @Test()
    void delete() {
        StateDao stateDao = new StateDaoImpl();
        stateDao.delete();
        stateDao.save("WHITE_TURN");
        stateDao.delete();

        assertThatThrownBy(stateDao::find)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 데이터가 없습니다.");
    }

    @DisplayName("데이터가 최신화 되는지 확인한다.")
    @Test()
    void update() {
        StateDao stateDao = new StateDaoImpl();
        stateDao.delete();
        stateDao.save("WHITE_TURN");
        stateDao.update("WHITE_TURN", "BLACK_TURN");

        assertThat(stateDao.find()).isEqualTo("BLACK_TURN");
    }
}
