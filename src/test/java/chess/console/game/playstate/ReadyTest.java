package chess.console.game.playstate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReadyTest {

    @ParameterizedTest
    @ValueSource(strings = {"move", "end", "start1"})
    @DisplayName("게임 시작 커맨드가 아니면 예외발생")
    void runException(String inputLine) {
        PlayState playState = new Ready();
        assertThatThrownBy(() -> playState.run(inputLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않아 다른 명령을 실행할 수 없습니다.");
    }

    @Test
    @DisplayName("run 실행 시 Running상태로 변경")
    void changeStateByRun() {
        PlayState playState = new Ready().run("start");
        assertThat(playState).isInstanceOf(Running.class);
    }
}
