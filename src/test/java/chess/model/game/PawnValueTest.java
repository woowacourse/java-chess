package chess.model.game;

import chess.model.position.Position;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnValueTest {

    @Test
    @DisplayName("Pawn의 기물 가치는 1점이다. 단, 같은 세로줄에 있으면 각 0.5점으로 계산한다.")
    void calculateScore() {
        // given
        PieceValue pawnValue = new PawnValue(1, 0.5);
        List<Position> positions = List.of(
                Position.of(File.A, Rank.FOUR),
                Position.of(File.A, Rank.TWO),
                Position.of(File.C, Rank.FIVE)
        );

        // when
        double score = pawnValue.calculateScore(positions);

        // then
        assertThat(score).isEqualTo(2);
    }
}
