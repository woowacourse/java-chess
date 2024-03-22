package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @Test
    @DisplayName("흑의 턴이 움직이면 백 차례 상태로 전이한다.")
    void whiteStateAfterMovingBlack() {
        // given
        BlackTurn blackTurn = new BlackTurn();
        // when
        GameState actual = blackTurn.proceedTurn((color) -> {});
        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }
}
