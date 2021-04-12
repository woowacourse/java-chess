package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {
    private Turn turn;

    @BeforeEach
    void setUp() {
        turn = new Turn();
    }

    @Test
    @DisplayName("검정말이 선인지 확인")
    void turnCheck() {
        final Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("검정 턴일 때 흰 턴으로 잘 바꾸는지 확인")
    void turnBlackCheck() {
        turn.next();
        final Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("흰 턴일 때 검정 턴으로 잘 바꾸는지 확인")
    void turnWhiteCheck() {
        turn.next();
        turn.next();
        final Player player = turn.player();
        assertThat(player.getColor()).isEqualTo(Color.BLACK);
    }
}