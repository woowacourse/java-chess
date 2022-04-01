package chess.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class CommandRequestTypeTest {

    @ParameterizedTest(name = "input : {0}, command : {1}")
    @MethodSource("provideForCommand")
    @DisplayName("해당하는 명령을 반환한다")
    void findCommand(final String input, final Command command) {
        assertThat(Command.of(input)).isEqualTo(command);
    }

    @Test
    @DisplayName("start 명령인지 비교한다.")
    void start() {
        assertThat(Command.START.isStart()).isTrue();
    }

    @Test
    @DisplayName("end 명령인지 비교한다.")
    void end() {
        assertThat(Command.END.isEnd()).isTrue();
    }

    @Test
    @DisplayName("move 명령인지 비교한다.")
    void move() {
        assertThat(Command.MOVE.isMove()).isTrue();
    }

    @Test
    @DisplayName("status 명령인지 비교한다.")
    void status() {
        assertThat(Command.STATUS.isStatus()).isTrue();
    }

    static Stream<Arguments> provideForCommand() {
        return Stream.of(
                Arguments.of("start", Command.START),
                Arguments.of("end", Command.END),
                Arguments.of("move", Command.MOVE)
        );
    }
}
