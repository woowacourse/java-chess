package chess.controller.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    private Finished finished;

    @BeforeEach
    void init() {
        finished = new Finished();
    }

    @Test
    @DisplayName("start시 에러를 return 한다.")
    void readyStart() {
        assertThatThrownBy(() -> finished.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("move시 에러를 return 한다.")
    void readyMoveFail() {
        assertThatThrownBy(() -> finished.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("status시 에러를 return 한다.")
    void readyStatusFail() {
        assertThatThrownBy(() -> finished.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("end시 finished를 return한다.")
    void readyEnd() {
        assertThatThrownBy(() -> finished.move("a1", "a2"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("isEnded는 false를 return한다.")
    void blackPlayingIsEnded() {
        //given & when
        boolean isEnded = finished.isEnded();

        //then
        assertThat(isEnded).isTrue();
    }
}