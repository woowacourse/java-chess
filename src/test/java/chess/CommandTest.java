package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandTest {

    @Test
    @DisplayName("Command가 start 또는 end가 아니면 예외가 발생한다.")
    void validateMenu() {
        assertThatThrownBy(() -> {
            new Command("aaa");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
