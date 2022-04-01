package chess.controller.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    private Ready ready;

    @BeforeEach
    void init() {
        ready = new Ready();
    }

    @Test
    @DisplayName("start시 WhitePlaying을 return 한다.")
    void readyStart() {
        //given & when
        ChessGameState stateAfterStart = ready.start();

        //then
        assertThat(stateAfterStart).isExactlyInstanceOf(WhitePlaying.class);
    }

    @Test
    @DisplayName("move시 에러를 return 한다.")
    void readyMoveFail() {
        assertThatThrownBy(() -> ready.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 시작 전에는 start 또는 end만 가능합니다.");
    }

    @Test
    @DisplayName("status시 에러를 return 한다.")
    void readyStatusFail() {
        assertThatThrownBy(() -> ready.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 시작 전에는 start 또는 end만 가능합니다.");
    }

    @Test
    @DisplayName("end시 finished를 return한다.")
    void blackPlayingEnd() {
        //given & when
        ChessGameState stateAfterEnd = ready.end();

        //then
        assertThat(stateAfterEnd).isExactlyInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("isEnded는 false를 return한다.")
    void blackPlayingIsEnded() {
        //given & when
        boolean isEnded = ready.isEnded();

        //then
        assertThat(isEnded).isFalse();
    }
}