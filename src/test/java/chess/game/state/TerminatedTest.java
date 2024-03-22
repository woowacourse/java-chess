package chess.game.state;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TerminatedTest {

    @Nested
    @DisplayName("게임이 종료됐을 때 ")
    class OnTerminated {

        @Test
        @DisplayName("진행 현황을 올바르게 반환한다.")
        void isPlayingTest() {
            // given
            Terminated terminated = new Terminated();
            // when
            boolean actual = terminated.isPlaying();
            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("모든 명령은 예외를 발생한다.")
        void startTest() {
            // given
            Terminated terminated = new Terminated();
            // when, then
            assertAll(
                () -> assertThatThrownBy(terminated::start)
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("게임이 이미 종료되었습니다."),
                () -> assertThatThrownBy(() -> terminated.proceedTurn(() -> {}))
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("게임이 이미 종료되었습니다."),
                () -> assertThatThrownBy(terminated::terminate)
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("게임이 이미 종료되었습니다.")
            );
        }
    }

}
