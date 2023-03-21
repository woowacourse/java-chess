package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameStatusTest {

    @ParameterizedTest
    @MethodSource("getGameStatus_TestCases")
    @DisplayName("커맨드를 받아서 게임 상태를 반환한다.")
    void getGameStatus(String command, GameStatus gameStatus) {
        // when, then
        assertThat(GameStatus.from(command)).isEqualTo(gameStatus);
    }

    static Stream<Arguments> getGameStatus_TestCases() {
        return Stream.of(
                Arguments.arguments("start", GameStatus.START),
                Arguments.arguments("move", GameStatus.MOVE),
                Arguments.arguments("end", GameStatus.END)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"star", "mo", "and"})
    @DisplayName("커맨드를 받아서 게임 상태를 반환한다.")
    void getGameStatus(String command) {
        // when, then
        assertThatThrownBy(() -> GameStatus.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 커맨드를 입력해주세요.");
    }
}
