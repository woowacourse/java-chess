package chess.status;

import chess.controller.Command;
import chess.controller.PrintAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    private final PrintAction dummyAction = ignored -> {
    };

    @Nested
    @DisplayName("playGame 메서드는")
    class playGame {

        @ParameterizedTest(name = "커맨드가 START가 아니면 예외를 발생시킨다")
        @ValueSource(strings = {"end", "status"})
        void it_throws_exception(String commandString) {
            final Command command = Command.from(List.of(commandString));

            assertThatThrownBy(() -> new Ready().playGame(command, dummyAction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임이 진행 중이지 않습니다");
        }

        @Test
        @DisplayName("커맨드가 START라면 Running을 반환한다")
        void it_initialize_game() {
            final Command command = Command.from(List.of("start"));

            assertThat(new Ready().playGame(command, dummyAction)).isInstanceOf(Running.class);
        }
    }
}
