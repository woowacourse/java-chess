package domain;

import static domain.Position.A1;
import static domain.Position.A2;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveCommandTest {

    @Test
    @DisplayName("옵션을 잘 해석하는지 검증")
    void getOptions() {
        MoveCommand moveCommand = new MoveCommand("a1", "a2");
        List<Position> options = moveCommand.getOptions();
        Assertions.assertThat(options)
                .containsExactly(A1, A2);
    }

    @Test
    @DisplayName("잘못된 옵션을 잘 해석할 수 없는지 검증")
    void getOptionsFailureCauseInvalidOption() {
        MoveCommand moveCommand = new MoveCommand("a9", "a2");
        Assertions.assertThatThrownBy(moveCommand::getOptions)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
