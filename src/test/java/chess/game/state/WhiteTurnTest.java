package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @Test
    @DisplayName("백의 턴이 움직이면 흑 차례 상태로 전이한다.")
    void whiteStateAfterMovingBlack() {
        // given
        WhiteTurn whiteTurn = new WhiteTurn();
        // when
        GameState actual = whiteTurn.proceedTurn(() -> {});
        // then
        assertThat(actual).isInstanceOf(BlackTurn.class);
    }
}
