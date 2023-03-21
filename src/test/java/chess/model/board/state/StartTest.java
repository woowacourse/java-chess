package chess.model.board.state;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.GameCommand;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StartTest {

    @ParameterizedTest(name = "{0}를 받으면 {1}이 반환된다.")
    @MethodSource("startParameters")
    void givenGameCommand_thenReturnGameState(final GameCommand command, final Class state) {
        // when, then
        assertThat(Start.from(command).getClass()).isEqualTo(state);
    }

    private static Stream<Arguments> startParameters() {
        return Stream.of(
                Arguments.of(START, Start.class),
                Arguments.of(END, End.class)
        );
    }

    @Test
    @DisplayName("시작하기전에는 move를 호출 할 수 없는지 테스트한다.")
    void cannotCallMove_WhenBeforeStart() {
        // when, then
        assertThatThrownBy(() -> Start.from(MOVE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작하기 전에 move를 호출 할 수 없습니다.");
    }

}
