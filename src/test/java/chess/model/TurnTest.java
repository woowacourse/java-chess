package chess.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("처음 Turn이 하얀색인지 확인한다.")
    void turn_whenCreate_thenIsWhite() {
        // given
        final Turn turn = new Turn();

        // when, then
        assertThat(turn.findCurrentPlayer().isWhite()).isTrue();
    }

    @Test
    @DisplayName("다음 Turn이 검정색인지 확인한다.")
    void turn_whenCallNext_thenIsBlack() {
        // given
        final Turn turn = new Turn();

        // when
        turn.next();

        // then
        assertThat(turn.findCurrentPlayer().isWhite()).isFalse();
    }
}
