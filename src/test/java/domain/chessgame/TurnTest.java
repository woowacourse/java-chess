package domain.chessgame;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("nextTurn을 호출하면 내부의 색상이 변한다.")
    void nextTurnTest() {
        //given
        final Turn turn = new Turn(Color.WHITE);
        //when
        final Turn nextTurn = turn.nextTurn();

        //then
        assertThat(nextTurn.getColor()).isEqualTo(Color.BLACK);
    }
}
