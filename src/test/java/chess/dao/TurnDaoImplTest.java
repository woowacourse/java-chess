package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnDaoImplTest {

    @DisplayName("데이터가 저장되는지 확인한다.")
    @Test()
    void save() {
        TurnDaoImpl turnDaoImpl = new TurnDaoImpl();
        turnDaoImpl.delete();
        turnDaoImpl.save("white");

        assertThat(turnDaoImpl.find()).isEqualTo("white");
    }

    @DisplayName("데이터가 삭제되는지 확인한다.")
    @Test()
    void delete() {
        TurnDaoImpl turnDaoImpl = new TurnDaoImpl();
        turnDaoImpl.delete();
        turnDaoImpl.save("white");
        turnDaoImpl.delete();

        assertThatThrownBy(turnDaoImpl::find)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 일치하는 데이터가 없습니다.");
    }

    @DisplayName("데이터가 최신화 되는지 확인한다.")
    @Test()
    void update() {
        TurnDaoImpl turnDaoImpl = new TurnDaoImpl();
        turnDaoImpl.delete();
        turnDaoImpl.save("white");
        turnDaoImpl.update("white", "black");

        assertThat(turnDaoImpl.find()).isEqualTo("black");
    }
}
