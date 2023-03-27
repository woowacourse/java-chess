package chess.model.piece.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.score.PieceScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceScoreTest {

    @Test
    @DisplayName("plus()는 더할 대상을 건네주면 기존 값과 더한 Score를 반환한다")
    void plus_givenPlusTarget_thenReturnPlusResult() {
        // given
        final PieceScore zero = PieceRuleScore.ZERO.score();

        // when
        final PieceScore actual = zero.plus(PieceRuleScore.BISHOP.score());

        // then
        assertThat(actual.getValue()).isEqualTo(3.0d);
    }

    @Test
    @DisplayName("minus()는 뺄 대상을 건네주면 기존 값과 뺀 Score를 반환한다")
    void minus_givenMinusTarget_thenReturnMinusResult() {
        // given
        final PieceScore pawn = PieceRuleScore.PAWN.score();

        // when
        final PieceScore actual = pawn.minus(PieceRuleScore.PAWN_OFFSET.score());

        // then
        assertThat(actual.getValue()).isEqualTo(0.5d);
    }
}
