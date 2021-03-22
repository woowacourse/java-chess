package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {
    @Test
    void turnCheck() {
        Turn turn = new Turn();
        Player player = turn.player();
        assertThat(player.color()).isEqualTo(Color.BLACK);
    }

    @Test
    void turnNextCheck() {
        Turn turn = new Turn();
        turn.next();
        Player player = turn.player();
        assertThat(player.color()).isEqualTo(Color.WHITE);
    }
}