package chess.controller.console.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandRouterTest {
    @DisplayName("콘솔 입력에 따른 알맞은 커맨드를 반환하는지 확인")
    @ParameterizedTest
    @MethodSource
    void commandRoutingTest(String inputCommand, Class<Command> command) {
        assertThat(CommandRouter.findByInputCommand(Arrays.asList(inputCommand.split(" ")))).isInstanceOf(command);
    }

    static Stream<Arguments> commandRoutingTest() {
        return Stream.of(
                Arguments.of("START", Start.class),
                Arguments.of("MOVE a1 a2", Move.class),
                Arguments.of("STATUS", Status.class),
                Arguments.of("End", End.class)
        );
    }

    @DisplayName("Start, End, Status 입력 시 다른 문자가 있으면 예외")
    @ParameterizedTest
    @ValueSource(strings = {"start dd", "end asd", "status a2 a3"})
    void throwExceptionWhenNotProperInputCommand(String test){
        assertThatThrownBy(() -> CommandRouter.findByInputCommand(Arrays.asList(test.split(" "))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은");
    }

    @DisplayName("move 명령 시 position이 잘못 된 경우")
    @ParameterizedTest
    @CsvSource(value = {"move a2, a3:위치 형식에 맞는 입력이 아닙니다.","move 123213:유효하지 않은 이동 명령입니다."}, delimiter = ':')
    void throwExceptionWhenNotProperInputMoveCommand(String test, String errorMsg){
        assertThatThrownBy(() -> CommandRouter.findByInputCommand(Arrays.asList(test.split(" "))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMsg);
    }
}