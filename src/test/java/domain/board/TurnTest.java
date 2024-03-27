package domain.board;

import domain.piece.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("현재 턴이 화이트인 경우 블랙 턴으로 변경한다.")
    void swap_WhiteTurn_BlackTurn() {
        Turn turn = new Turn(Color.WHITE);

        turn.swap();

        assertThat(turn).isEqualTo(new Turn(Color.BLACK));
    }

    @Test
    @DisplayName("현재 턴이 화이트인 경우 블랙 턴으로 변경한다.")
    void swap_BlackTurn_WhiteTurn() {
        Turn turn = new Turn(Color.BLACK);

        turn.swap();

        assertThat(turn).isEqualTo(new Turn(Color.WHITE));
    }

    @Test
    void validateTurn() {
        Turn whiteTurn = new Turn(Color.WHITE);

        Assertions.assertThatThrownBy(() -> whiteTurn.validate(Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
