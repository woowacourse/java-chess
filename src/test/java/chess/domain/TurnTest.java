package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private Turn turn;

    @BeforeEach
    void setUp() {
        turn = new Turn();
    }

    @Test
    @DisplayName("검정말이 선인지 확인")
    void turnCheck() {
        Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("다음 턴으로 잘 바꾸는지 확인")
    void turnNextCheck() {
        turn.next();
        Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.WHITE);
    }
}