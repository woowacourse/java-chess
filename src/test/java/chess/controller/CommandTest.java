package chess.controller;

import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CommandTest {

    @ParameterizedTest(name = "커맨드 메뉴에 존재하는 커맨드 입력 테스트")
    @MethodSource("generateCommandSource")
    void hasCommand(String inputCommand, Command command) {

        // when
        Command foundCommand = Command.findCommandByInputCommand(inputCommand);

        // then
        assertThat(foundCommand).isEqualTo(command);
    }

    private static Stream<Arguments> generateCommandSource() {
        return Stream.of(
                Arguments.of("start", Command.START),
                Arguments.of("move", Command.MOVE),
                Arguments.of("status", Command.STATUS),
                Arguments.of("end", Command.END),
                Arguments.of("exit", Command.EXIT)
        );
    }

    @Test
    @DisplayName("커맨드 메뉴에 존재하지 않는 커맨드 입력 테스트")
    void hasCommand_DoesNotHaveCommand_False() {

        // given
        String command = "test";

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> Command.findCommandByInputCommand(command);

        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("메뉴에 없는 커맨드입니다. 다시 입력해주세요.");
    }
}
