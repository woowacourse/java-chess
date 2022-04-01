package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.fixedmovablepiece.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.straightmovablepiece.Bishop;
import chess.domain.piece.straightmovablepiece.Queen;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    @Test
    @DisplayName("한 컬럼의 점수를 계산한다.")
    void calculateOneColumn() {
        final Pieces pieces = new Pieces(List.of(new Knight(Color.BLACK), new Bishop(Color.BLACK)));
        final ScoreCalculator calculator = ScoreCalculator.getInstance();
        final double score = calculator.calculateOneColumn(pieces);

        assertThat(score).isEqualTo(5.5);
    }

    @Test
    @DisplayName("폰이 두 개 이상 포함된 한 컬럼의 점수를 계산한다.")
    void calculateOneColumnWithPawns() {
        final Pieces pieces = new Pieces(List.of(new Knight(Color.BLACK), new Bishop(Color.BLACK),
                new BlackPawn(), new BlackPawn(), new BlackPawn()));
        final ScoreCalculator calculator = ScoreCalculator.getInstance();
        final double score = calculator.calculateOneColumn(pieces);

        assertThat(score).isEqualTo(7);
    }

    @Test
    @DisplayName("여러 컬럼의 점수를 계산한다.")
    void calculateColumns() {
        final List<Pieces> pieces = List.of(
                new Pieces(List.of(
                        new Knight(Color.BLACK), new Bishop(Color.BLACK),
                        new BlackPawn(), new BlackPawn(), new BlackPawn())
                ),
                new Pieces(List.of(
                        new BlackPawn(), new BlackPawn(), new Queen(Color.BLACK))
                )
        );
        final ScoreCalculator calculator = ScoreCalculator.getInstance();
        final double score = calculator.calculateColumns(pieces);

        assertThat(score).isEqualTo(17);
    }
}
