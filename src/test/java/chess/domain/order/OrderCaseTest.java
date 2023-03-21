package chess.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderCaseTest {

    @Test
    @DisplayName("start 명령어를 받으면 START를 반환한다")
    void from_start() {
        final List<String> command = List.of("start");

        assertThat(OrderCase.from(command)).isEqualTo(OrderCase.START);
    }

    @Test
    @DisplayName("end 명령어를 받으면 END를 반환한다")
    void from_end() {
        final List<String> command = List.of("end");

        assertThat(OrderCase.from(command)).isEqualTo(OrderCase.END);
    }

    @Test
    @DisplayName("move 명령어를 받으면 MOVE를 반환한다")
    void from_move() {
        final List<String> command = List.of("move", "a", "b");

        assertThat(OrderCase.from(command)).isEqualTo(OrderCase.MOVE);
    }

    @Test
    @DisplayName("start, end, move가 아닌 다른 값을 받으면 예외가 발생한다")
    void invalidCommand() {
        final List<String> command = List.of("test");

        assertThatThrownBy(() -> OrderCase.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 start, end, move만 가능합니다.");
    }

    @Test
    @DisplayName("START는 이름을 가진다")
    void getValue_START() {
        assertThat(OrderCase.START.getValue()).isEqualTo("start");
    }

    @Test
    @DisplayName("END는 이름을 가진다")
    void getValue_END() {
        assertThat(OrderCase.END.getValue()).isEqualTo("end");
    }

    @Test
    @DisplayName("MOVE는 이름을 가진다")
    void getValue_MOVE() {
        assertThat(OrderCase.MOVE.getValue()).isEqualTo("move");
    }
}
