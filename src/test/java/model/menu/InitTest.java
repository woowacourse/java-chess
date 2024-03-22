package model.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitTest {

    @DisplayName("초기 상태에서 start를 입력하면 Running을 반환한다.")
    @Test
    void gameSettingStart() {
        assertThat(Init.gameSetting(List.of("start"))).isInstanceOf(Running.class);
    }

    @DisplayName("초기 상태에서 end를 입력하면 End를 반환한다.")
    @Test
    void gameSettingEnd() {
        assertThat(Init.gameSetting(List.of("end"))).isInstanceOf(End.class);
    }

    @DisplayName("초기 상태에서 Move명령어를 입력하면 예외가 발생한다.")
    @Test
    void invalidGameSetting() {
        assertThatThrownBy(() -> Init.gameSetting(List.of("move", "a2", "a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start를 입력해야 게임이 시작됩니다.");
    }
}
