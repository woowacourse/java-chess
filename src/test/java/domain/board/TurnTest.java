package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnTest {

    @Test
    @DisplayName("상대 턴을 반환한다.")
    void next_opposite() {
        Turn turn = new Turn(Color.WHITE);

        Turn actual = turn.next();
        Turn expected = new Turn(Color.BLACK);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("해당 턴이 아니면 참을 반환한다.")
    void isNotTurn_True() {
        Turn turn = new Turn(Color.WHITE);
        assertThat(turn.isNotTurn(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("해당 턴이 맞으면 거짓을 반환한다.")
    void isNotTurn_False() {
        Turn turn = new Turn(Color.WHITE);
        assertThat(turn.isNotTurn(Color.WHITE)).isFalse();
    }
}
