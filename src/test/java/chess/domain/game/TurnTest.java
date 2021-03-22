package chess.domain.game;

import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {
    @Test
    void turnCheck() {
        Turn turn = new Turn();
        assertThat(turn.color()).isEqualTo(Color.BLACK);
    }

    @Test
    void turnNextCheck() {
        Turn turn = new Turn();
        turn.next();
        assertThat(turn.color()).isEqualTo(Color.WHITE);
    }
}