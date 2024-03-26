package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class SquareTest {

    @Nested
    class isStraightTest {

        @DisplayName("출발지에서 목적지까지 수직으로 이동하면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"ONE", "EIGHT"})
        void returnTrueWhenMoveVertical(final Rank rank) {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.e, rank);

            final boolean actual = source.isStraight(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("출발지에서 목적지까지 수평으로 이동하면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"a", "h"})
        void returnTrueWhenMoveHorizontal(final File file) {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, Rank.FOUR);

            final boolean actual = source.isStraight(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("출발지에서 목적지까지 수직, 수평으로 이동하지 않으면 False를 리턴한다.")
        @Test
        void returnFalseWhenCannotMoveStraight() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.f, Rank.FIVE);

            final boolean actual = source.isStraight(target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isDiagonalTest {

        @DisplayName("출발지에서 목적지까지 대각선으로 이동하면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"a, EIGHT", "h, SEVEN", "b, ONE", "h, ONE"})
        void returnTrueWhenMoveDiagonal(final File file, final Rank rank) {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            final boolean actual = source.isDiagonal(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("출발지에서 목적지까지 대각선으로 이동하지 않으면 False를 리턴한다.")
        @Test
        void returnFalseWhenNotMoveDiagonal() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.e, Rank.FIVE);

            final boolean actual = source.isDiagonal(target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isWithinOneStepTest {

        @DisplayName("출발지부터 목적지까지 한 칸 이내로 이동한 경우 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"d, FIVE", "e, FIVE", "f, FIVE", "f, FOUR", "f, THREE", "e, THREE", "d, THREE", "d, FOUR"})
        void returnTrueWhenMoveWithinOneStep(final File file, final Rank rank) {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(file, rank);

            final boolean actual = source.isWithinOneStep(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("출발지부터 목적지까지 한 칸을 초과하여 이동한 경우 False를 리턴한다.")
        @Test
        void returnFalseWhenMoveMoreThanOneStep() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.d, Rank.SIX);

            final boolean actual = source.isWithinOneStep(target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isStraightAndDiagonalForKnightTest {

        @DisplayName("열 한 칸, 행 두 칸을 이동한 경우 True를 리턴한다.")
        @Test
        void returnTrueWhenMoveFileOneStepAndRankTwoStep() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.f, Rank.SIX);

            final boolean actual = source.isStraightAndDiagonal(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("열 두 칸, 행 한 칸을 이동한 경우 True를 리턴한다.")
        @Test
        void returnTrueWhenMoveFileTwoStepAndRankOneStep() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.c, Rank.FIVE);

            final boolean actual = source.isStraightAndDiagonal(target);

            assertThat(actual).isTrue();
        }
    }

    @Nested
    class isNotBackwardForPawnTest {

        @DisplayName("검정색 폰이 아래로 이동하면 True를 리턴한다.")
        @Test
        void returnTrueWhenBlackPawnMoveDown() {
            final PieceColor color = PieceColor.BLACK;
            final Square source = new Square(File.e, Rank.SEVEN);
            final Square target = new Square(File.e, Rank.FIVE);

            final boolean actual = source.isNotBackward(target, color);

            assertThat(actual).isTrue();
        }

        @DisplayName("검정색 폰이 위로 이동하면 False를 리턴한다.")
        @Test
        void returnFalseWhenBlackPawnMoveUp() {
            final PieceColor color = PieceColor.BLACK;
            final Square source = new Square(File.e, Rank.SEVEN);
            final Square target = new Square(File.e, Rank.EIGHT);

            final boolean actual = source.isNotBackward(target, color);

            assertThat(actual).isFalse();
        }

        @DisplayName("흰색 폰이 위로 이동하면 True를 리턴한다.")
        @Test
        void returnTrueWhenWhitePawnMoveUp() {
            final PieceColor color = PieceColor.WHITE;
            final Square source = new Square(File.e, Rank.TWO);
            final Square target = new Square(File.e, Rank.FOUR);

            final boolean actual = source.isNotBackward(target, color);

            assertThat(actual).isTrue();
        }

        @DisplayName("흰색 폰이 아래로 이동하면 False를 리턴한다.")
        @Test
        void returnFalseWhenWhitePawnMoveDown() {
            final PieceColor color = PieceColor.WHITE;
            final Square source = new Square(File.e, Rank.TWO);
            final Square target = new Square(File.e, Rank.ONE);

            final boolean actual = source.isNotBackward(target, color);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isOnlyForwardForPawnTest {

        @DisplayName("폰은 첫 번째 이동 시, 수직으로 최대 두 칸까지 이동하면 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"TWO, e, THREE", "TWO, e, FOUR", "SEVEN, e, SIX", "SEVEN, e, FIVE"})
        void returnTrueWhenPawnMoveUpToTwoStepWhenFirstMove(
                final Rank sourceRank, final File file, final Rank targetRank) {
            final Square source = new Square(File.e, sourceRank);
            final Square target = new Square(file, targetRank);

            final boolean actual = source.isOnlyForward(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("폰은 두 번째 이동부터는 수직으로 한 칸씩만 이동할 경우 True를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"THREE, e, FOUR", "SIX, e, FIVE"})
        void returnTrueWhenPawnMoveOnlyOneStepAfterFirstMove(
                final Rank sourceRank, final File file, final Rank targetRank) {
            final Square source = new Square(File.e, sourceRank);
            final Square target = new Square(file, targetRank);

            final boolean actual = source.isOnlyForward(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("두 번째 이동부터 수직으로 두 칸 이상 이동할 경우 False를 리턴한다.")
        @Test
        void returnFalseIfExceedStepLimitAfterFirstMove() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.e, Rank.SIX);

            final boolean actual = source.isOnlyForward(target);

            assertThat(actual).isFalse();
        }

        @DisplayName("수직으로 이동하지 않으면 False를 리턴한다.")
        @Test
        void returnFalseWhenNotVertical() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.f, Rank.FOUR);

            final boolean actual = source.isOnlyForward(target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class isAttackForPawnTest {

        @DisplayName("대각선 한 칸 위에 다른 팀의 기물이 있어 공격할 수 있는 경우 True를 리턴한다.")
        @Test
        void returnTrueWhenCanAttack() {
            final Square source = new Square(File.e, Rank.THREE);
            final Square target = new Square(File.f, Rank.FOUR);

            final boolean actual = source.isAttack(target);

            assertThat(actual).isTrue();
        }

        @DisplayName("공격할 수 없는 경우 False를 리턴한다.")
        @ParameterizedTest
        @CsvSource({"e, FIVE", "d, SIX"})
        void returnFalseWhenCannotAttack(final File file, final Rank rank) {
            final Square source = new Square(File.e, Rank.THREE);
            final Square target = new Square(file, rank);

            final boolean actual = source.isAttack(target);

            assertThat(actual).isFalse();
        }
    }

    @Nested
    class findPathTest {

        @DisplayName("출발지에서 목적지까지 직선으로 이동하는 경우의 경로를 생성한다.")
        @Test
        void createPathWhenStraight() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.a, Rank.FOUR);

            final List<Square> actual = source.findPath(target);

            assertThat(actual).containsExactlyInAnyOrder(
                    new Square(File.b, Rank.FOUR),
                    new Square(File.c, Rank.FOUR),
                    new Square(File.d, Rank.FOUR));
        }

        @DisplayName("출발지에서 목적지까지 대각선으로 이동하는 경우의 경로를 생성한다.")
        @Test
        void createPathWhenDiagonal() {
            final Square source = new Square(File.e, Rank.FOUR);
            final Square target = new Square(File.a, Rank.EIGHT);

            final List<Square> actual = source.findPath(target);

            assertThat(actual).containsExactlyInAnyOrder(
                    new Square(File.d, Rank.FIVE),
                    new Square(File.c, Rank.SIX),
                    new Square(File.b, Rank.SEVEN));
        }

        @DisplayName("출발지에서 목적지까지 직선이나 대각선으로 이동하지 않는 경우 경로가 생성되지 않는다.")
        @Test
        void notCreatePathWhenNotStraightAndDiagonal() {
            final Square source = new Square(File.e, Rank.TWO);
            final Square target = new Square(File.d, Rank.FIVE);

            final List<Square> actual = source.findPath(target);

            assertThat(actual).isEmpty();
        }
    }
}
