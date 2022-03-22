package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameSwitchTest {

    @Test
    @DisplayName("start를 변수로 넣을 때 START를 반환한다.")
    void ofStart() {
        assertThat(GameSwitch.of("start")).isEqualTo(GameSwitch.START);
    }

    @Test
    @DisplayName("START를 변수로 넣을 때 START를 반환한다.")
    void ofStartIgnoreCase() {
        assertThat(GameSwitch.of("START")).isEqualTo(GameSwitch.START);
    }

    @Test
    @DisplayName("end를 변수로 넣을 때 END를 반환한다.")
    void ofEnd() {
        assertThat(GameSwitch.of("end")).isEqualTo(GameSwitch.END);
    }

    @Test
    @DisplayName("END를 변수로 넣을 때 END를 반환한다.")
    void ofEndIgnoreCase() {
        assertThat(GameSwitch.of("END")).isEqualTo(GameSwitch.END);
    }

    @Test
    @DisplayName("start를 변수로 넣을 때 START를 반환한다.")
    void ofException() {
        assertThatThrownBy(() -> GameSwitch.of("acaca"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 입력입니다.");
    }

}
