package domain.util;

import domain.Board;
import domain.Turn;
import domain.piece.Textures;
import domain.piece.bishop.WhiteBishop;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.queen.BlackQueen;
import domain.piece.rook.BlackRook;
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
                new Point(File.F, Rank.FOUR), new BlackRook(),
                new Point(File.D, Rank.FOUR), new BlackQueen(),
                new Point(File.E, Rank.SIX), new WhiteBishop(),
                new Point(File.A, Rank.TWO), new WhitePawn()
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
                new Point(File.F, Rank.FOUR), new WhitePawn(),
                new Point(File.F, Rank.FIVE), new BlackPawn(),
                new Point(File.F, Rank.TWO), new BlackRook(),
                new Point(File.F, Rank.ONE), new BlackPawn()
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
