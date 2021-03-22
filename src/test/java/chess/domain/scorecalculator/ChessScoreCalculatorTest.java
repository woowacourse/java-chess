package chess.domain.scorecalculator;

import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessScoreCalculatorTest {

    @Test
    @DisplayName("queen - 9점, bishop - 3점, knight - 2.5점 > 총 14.5점")
    void calculate_pieces() {
        ScoreCalculator scoreCalculator = new ChessScoreCalculator();
        List<Piece> pieces = Arrays.asList(
            new Queen(WHITE, Position.of(1, 1)),
            new Bishop(WHITE, Position.of(1, 2)),
            new Knight(WHITE, Position.of(1, 3))
        );

        Score score = scoreCalculator.calculate(pieces);
        Assertions.assertThat(score).isEqualTo(Score.from(14.5));
    }

    @Test
    @DisplayName("같은 column이 아닌 폰의 점수 계산")
    void calculatePawnTest_notSameColumn() {
        ScoreCalculator scoreCalculator = new ChessScoreCalculator();

        List<Piece> pieces = Arrays.asList(
            new Pawn(WHITE, Position.of(1, 1)),
            new Pawn(WHITE, Position.of(2, 1)),
            new Pawn(WHITE, Position.of(3, 1))
        );

        Score score = scoreCalculator.calculate(pieces);
        Assertions.assertThat(score).isEqualTo(Score.from(3));
    }

    @Test
    @DisplayName("같은 column에 있는 폰의 점수 계산")
    void calculatePawnTest_sameColumn() {
        ScoreCalculator scoreCalculator = new ChessScoreCalculator();

        List<Piece> pieces = Arrays.asList(
            new Pawn(WHITE, Position.of(1, 1)),
            new Pawn(WHITE, Position.of(1, 2)),
            new Pawn(WHITE, Position.of(1, 3))
        );

        Score score = scoreCalculator.calculate(pieces);
        Assertions.assertThat(score).isEqualTo(Score.from(1.5));
    }


}