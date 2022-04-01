package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("초기에 화이트 턴이 맞는지 확인")
    void checkWhiteTurn() {
        Turn turn = new Turn();

        assertThat(turn.isRightTurn(Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("턴이 바뀌는지 확인")
    void checkChangeTurn() {
        Turn turn = new Turn();
        turn.nextTurn();

        assertThat(turn.isRightTurn(Color.BLACK)).isTrue();
    }
}
