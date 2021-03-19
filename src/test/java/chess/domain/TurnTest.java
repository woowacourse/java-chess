package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    @Test
    void turnCheck() {
        Turn turn = new Turn();
        Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    void turnNextCheck() {
        Turn turn = new Turn();
        turn.next();
        Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.WHITE);
    }
}