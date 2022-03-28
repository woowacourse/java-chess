package chess.console.game.playstate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EndTest {

    @ParameterizedTest
    @ValueSource(strings = {"start", "move", "end", "input"})
    @DisplayName("종료 상태에서 run 실행시 예외발생")
    void runException(String inputLine) {
        PlayState playState = new End();
        assertThatThrownBy(() -> playState.run(inputLine))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료된 상태에서는 게임을 실행할 수 없습니다.");
    }
}
