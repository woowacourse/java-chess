package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Side는")
class SideTest {

    @DisplayName("BLACK에게 WHITE와 상대 진영인지 물으면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenRequestToBlackIfOpponentSideWithWhite() {
        Side side = Side.BLACK;
        assertThat(side.isOpponentWith(Side.WHITE)).isTrue();
    }

    @DisplayName("BLACK에게 BLACK과 상대 진영인지 물으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenRequestToWhiteIfOpponentSideWithWhite() {
        Side side = Side.BLACK;
        assertThat(side.isOpponentWith(Side.BLACK)).isFalse();
    }

    @DisplayName("NEUTRAL에게 WHITE와 상대 진영인지 물으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenRequestToNeutralIfOpponentSideWithWhite() {
        Side side = Side.NEUTRAL;
        assertThatThrownBy(() -> side.isOpponentWith(Side.WHITE))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("서버 내부 에러 - Neutral side는 상대편을 확인할 수 없습니다.");
    }
}
