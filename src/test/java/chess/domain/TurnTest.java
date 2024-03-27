package chess.domain;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @DisplayName("다음 차례의 색으로 바뀐다.")
    @Test
    void nextTurn() {
        //given
        Turn turn = Turn.first();

        //when
        turn.next();

        //then
        assertThat(turn.isNotTurnOwner(PieceColor.WHITE)).isTrue();
    }
}
