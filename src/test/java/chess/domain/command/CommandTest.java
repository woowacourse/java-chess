package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommandTest {

    static Stream<Arguments> createCommand() {
        return Stream.of(
                Arguments.of("Start", Command.START),
                Arguments.of("stArt", Command.START),
                Arguments.of("end", Command.END),
                Arguments.of("END", Command.END)
        );
    }

    static Stream<Arguments> createBoolean() {
        return Stream.of(
                Arguments.of(Command.START, true),
                Arguments.of(Command.END, false)
        );
    }

    @ParameterizedTest
    @DisplayName("from 팩토리 메서드")
    @MethodSource("createCommand")
    void from(String command, Command expected) {
        assertThat(Command.from(command)).isEqualTo(expected);
    }

    @Test
    @DisplayName("팩토리 메서드 검증")
    void validate() {
        assertThatThrownBy(() -> {
            Command.from("strat");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("strat는 잘못된 입력입니다.");
    }

    @ParameterizedTest
    @DisplayName("Command가 시작인지 확인")
    @MethodSource("createBoolean")
    void isStart(Command command, boolean expected) {
        assertThat(command.isStart()).isEqualTo(expected);
    }
}