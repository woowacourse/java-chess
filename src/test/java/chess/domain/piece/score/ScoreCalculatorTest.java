package chess.domain.piece.score;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.score.Score;
import domain.piece.score.ScoreCalculator;
import domain.piece.type.Pawn;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;

class ScoreCalculatorTest {

    @Test
    @DisplayName("pieceList를 이용해 점수 총합을 구한다.")
    void calculateSum() {
        List<Piece> pieces = List.of(new Pawn(Camp.WHITE), new Rook(Camp.WHITE), new Queen(Camp.WHITE));
        Score score = ScoreCalculator.calculateSum(pieces);

        assertThat(score).isEqualTo(new Score(15));
    }
}
