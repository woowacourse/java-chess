package view.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("동일한 식별자가 있는지 확인한다.")
    @Test
    void isSameIdentifier() {
        assertThat(Command.from("start")).isEqualTo(Command.START);
    }

    @DisplayName("동일한 식별자가 존재하지 않는다.")
    @Test
    void isNotSameIdentifier() {
        assertThatThrownBy(() -> Command.from("pola"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당되는 식별자가 존재하지 않습니다.");
    }
}
