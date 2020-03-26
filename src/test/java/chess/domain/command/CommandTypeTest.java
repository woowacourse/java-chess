package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommandTypeTest {

    @ParameterizedTest
    @DisplayName("from 팩토리 메서드")
    @MethodSource("createCommand")
    void from(String command, CommandType expected) {
        assertThat(chess.domain.command.CommandType.from(command)).isEqualTo(expected);
    }

    static Stream<Arguments> createCommand() {
        return Stream.of(
                Arguments.of("Start", chess.domain.command.CommandType.START),
                Arguments.of("stArt", chess.domain.command.CommandType.START),
                Arguments.of("end", chess.domain.command.CommandType.END),
                Arguments.of("END", chess.domain.command.CommandType.END)
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