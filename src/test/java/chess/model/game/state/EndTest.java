package chess.model.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.PlayRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private final GameState end = new End();

    @Test
    @DisplayName("execute()는 호출하면 예외가 발생한다.")
    void execute_whenCall_thenFail() {
        // given
        final PlayRequest request = new PlayRequest("end", "Z0", "Z0");

        // when, then
        assertThatThrownBy(() -> end.execute(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("isContinue()는 호출하면 false를 반환한다.")
    void isContinue_whenCall_thenReturnFalse() {
        // when
        final boolean actual = end.isContinue();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isPlay()는 호출하면 false를 반환한다.")
    void isPlay_whenCall_thenReturnFalse() {
        // when
        final boolean actual = end.isPlay();

        // then
        assertThat(actual).isFalse();
    }
}
