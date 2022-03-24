package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    @DisplayName("start를 변수로 넣을 때 START를 반환한다.")
    void ofStart() {
        assertThat(Command.of("start")).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("START를 변수로 넣을 때 START를 반환한다.")
    void ofStartIgnoreCase() {
        assertThat(Command.of("START")).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("end를 변수로 넣을 때 END를 반환한다.")
    void ofEnd() {
        assertThat(Command.of("end")).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("END를 변수로 넣을 때 END를 반환한다.")
    void ofEndIgnoreCase() {
        assertThat(Command.of("END")).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("start를 변수로 넣을 때 START를 반환한다.")
    void ofException() {
        assertThatThrownBy(() -> Command.of("acaca"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 입력입니다.");
    }

}
