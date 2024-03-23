package domain.board;

import domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {
    @Test
    void 첫_턴은_WHITE이다() {
        Turn turn = new Turn();

        assertThat(turn.isOpponentTurn(Color.BLACK)).isTrue();
    }

    @Test
    void 턴이_1번_정상적으로_변경된다() {
        Turn turn = new Turn();

        turn.flip();

        assertThat(turn.isOpponentTurn(Color.WHITE)).isTrue();
    }

    @Test
    void 턴이_2번_정상적으로_변경된다() {
        Turn turn = new Turn();

        turn.flip();
        turn.flip();

        assertThat(turn.isOpponentTurn(Color.BLACK)).isTrue();
    }

    @Test
    void 턴이_3번_정상적으로_변경된다() {
        Turn turn = new Turn();

        turn.flip();
        turn.flip();
        turn.flip();

        assertThat(turn.isOpponentTurn(Color.WHITE)).isTrue();
    }
}
