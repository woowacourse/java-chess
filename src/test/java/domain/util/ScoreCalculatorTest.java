package domain.util;

import domain.Board;
import domain.Turn;
import domain.piece.Bishop;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Textures;
import domain.piece.Pawn;
import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ScoreCalculator;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreCalculatorTest {
    @Test
    @DisplayName("현재까지 남아 있는 말에 따라 각각 점수를 계산한다.")
    void score() {
        // given
        Board board = Textures.makeBoard(Map.of(
                new Point(File.F, Rank.FOUR), new Rook(Turn.BLACK),
                new Point(File.D, Rank.FOUR), new Queen(Turn.BLACK),
                new Point(File.E, Rank.SIX), new Bishop(Turn.WHITE),
                new Point(File.A, Rank.TWO), new Pawn(Turn.WHITE)
        ));

        // when
        float blackScore = ScoreCalculator.calculate(board.findCurrentStatus(), Turn.BLACK);
        float whiteScore = ScoreCalculator.calculate(board.findCurrentStatus(), Turn.WHITE);

        // then
        assertAll(
                () -> assertThat(blackScore).isEqualTo(14),
                () -> assertThat(whiteScore).isEqualTo(4)
        );
    }

    @Test
    @DisplayName("같은 세로줄에 같은 색깔의 폰이 있는 경우, 폰의 점수는 1이 아니라 0.5다.")
    void pawnCase() {
        // given
        Board board = Textures.makeBoard(Map.of(
                new Point(File.F, Rank.FOUR), new Pawn(Turn.WHITE),
                new Point(File.F, Rank.FIVE), new Pawn(Turn.BLACK),
                new Point(File.F, Rank.TWO), new Rook(Turn.BLACK),
                new Point(File.F, Rank.ONE), new Pawn(Turn.BLACK)
        ));

        // when
        float blackScore = ScoreCalculator.calculate(board.findCurrentStatus(), Turn.BLACK);
        float whiteScore = ScoreCalculator.calculate(board.findCurrentStatus(), Turn.WHITE);

        // then
        assertAll(
                () -> assertThat(blackScore).isEqualTo(6),
                () -> assertThat(whiteScore).isEqualTo(1)
        );
    }
}
