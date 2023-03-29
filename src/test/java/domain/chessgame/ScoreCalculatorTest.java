package domain.chessgame;

import domain.piece.*;
import domain.squarestatus.Empty;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreCalculatorTest {

    @Test
    @DisplayName("Pawn을 제외한 기물의 리스트와 Pawn의 Position 리스트를 입력하면 점수를 계산한다.")
    void calculateScoreTest() {
        //given
        final List<SquareStatus> pieces = List.of(new King(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE), new Empty(EmptyType.EMPTY));
        final List<Long> pawns = List.of(1L, 2L);

        //when
        final double score = ScoreCalculator.calculateScore(pieces, pawns);

        //then
        Assertions.assertThat(score).isEqualTo(16.5);
    }

}
