package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InitializationTest {

    @DisplayName("start를 입력하면 게임이 시작된다.")
    @Test
    void gameStartWhenCommandIsStart() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        assertThat(gameStatus).isInstanceOf(Running.class);
    }

    @DisplayName("end를 입력하면 게임이 종료된다.")
    @Test
    void gameEndWhenCommandIsEnd() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("end"));
        assertThat(gameStatus).isInstanceOf(End.class);
    }

    @DisplayName("시작시 유효하지 않은 명령어가 오면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidCommandParameterProvider")
    void invalidCommand(List<String> command) {
        assertThatThrownBy(() -> Initialization.gameSetting(command))
                .isInstanceOf(InvalidStatusException.class);
    }

    static Stream<Arguments> invalidCommandParameterProvider() {
        return Stream.of(
                Arguments.of(List.of("start", "a1")),
                Arguments.of(List.of("start", "start")),
                Arguments.of(List.of("start", "end")),
                Arguments.of(List.of("end", "start"))
        );
    }
}
