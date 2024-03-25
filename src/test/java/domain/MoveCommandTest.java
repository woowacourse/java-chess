package domain;

import static domain.Position.A1;
import static domain.Position.A2;
import static org.junit.jupiter.api.Assertions.*;

import domain.command.MoveCommand;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveCommandTest {

    @Test
    @DisplayName("옵션을 잘 해석하는지 검증")
    void getOptions() {
        MoveCommand moveCommand = new MoveCommand("a1", "a2");
        assertAll(
                () -> Assertions.assertThat(moveCommand.getFrom())
                        .isEqualTo(A1),
                () -> Assertions.assertThat(moveCommand.getTo())
                                .isEqualTo(A2)
        );
    }

    @Test
    @DisplayName("잘못된 옵션을 잘 해석할 수 없는지 검증")
    void getOptionsFailureCauseInvalidOption() {
        Assertions.assertThatThrownBy(() -> new MoveCommand("a9", "a2"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
