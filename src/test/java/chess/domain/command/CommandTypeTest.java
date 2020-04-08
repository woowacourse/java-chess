package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTypeTest {

    @ParameterizedTest
    @DisplayName("from 팩토리 메서드")
    @MethodSource("createCommand")
    void from(String command, CommandType expected) {
        assertThat(chess.domain.command.CommandType.from(command)).isEqualTo(expected);
    }

    static Stream<Arguments> createCommand() {
        return Stream.of(
                Arguments.of("Start", CommandType.START),
                Arguments.of("stArt", CommandType.START),
                Arguments.of("end", CommandType.END),
                Arguments.of("END", CommandType.END),
                Arguments.of("moVE", CommandType.MOVE),
                Arguments.of("Move", CommandType.MOVE),
                Arguments.of("Status", CommandType.STATUS),
                Arguments.of("STATUS", CommandType.STATUS)

        );
    }

    @Test
    @DisplayName("팩토리 메서드 검증")
    void validate() {
        assertThatThrownBy(() -> {
            chess.domain.command.CommandType.from("strat");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("strat는 잘못된 입력입니다.");
    }
}