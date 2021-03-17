package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    private static Stream<Arguments> getCommandInputAndEnum() {
        return Stream.of(Arguments.of("start", Command.START),
            Arguments.of("move", Command.MOVE),
            Arguments.of("end", Command.END));
    }

    @DisplayName("명령어에 해당하는 Enum을 탐색한다")
    @ParameterizedTest
    @MethodSource("getCommandInputAndEnum")
    void findCommandEnum(String commandInput, Command expectCommand) {
        Command command = Command.findCommand(commandInput);

        assertThat(command).isEqualTo(expectCommand);
    }

    @DisplayName("정의되지 않은 명령어 입력은 예외를 발생시킨다")
    @Test
    void throwExceptionWhenInvalidCommand() {
        assertThatCode(() -> Command.findCommand("invalid"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("명령어를 잘못 입력했습니다.");
    }
}