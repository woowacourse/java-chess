package chess.model.evaluation;

import chess.model.position.Position;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommonValueTest {

    @Test
    @DisplayName("기물 가치에 따라 점수를 계산한다.")
    void calculateScore() {
        // given
        PieceValue pieceValue = new CommonValue(5);
        List<Position> positions = List.of(
                Position.of(File.A, Rank.FOUR),
                Position.of(File.A, Rank.TWO),
                Position.of(File.C, Rank.FIVE)
        );

        // when
        double score = pieceValue.calculateScore(positions);

        // then
        assertThat(score).isEqualTo(15);
    }
}
