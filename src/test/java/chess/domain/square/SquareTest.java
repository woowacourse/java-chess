package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("좌표")
class SquareTest {

    @DisplayName("출발지에서 목적지까지 세로로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveVertical() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.e, Rank.SEVEN);

        boolean actual = source.isStraight(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 가로로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveHorizontal() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.a, Rank.FOUR);

        boolean actual = source.isStraight(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveDiagonal() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.h, Rank.SEVEN);

        boolean actual = source.isDiagonal(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지부터 목적지까지 한 칸 이내로 이동한 경우 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveWithinOneStep() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.e, Rank.FIVE);

        boolean actual = source.isWithinOneStep(target);

        assertThat(actual).isTrue();
    }


    @DisplayName("출발지부터 목적지까지 한 칸을 초과하여 이동한 경우 False를 리턴한다.")
    @Test
    void returnFalseWhenMoveMoreThanOneStep() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.e, Rank.SEVEN);

        boolean actual = source.isWithinOneStep(target);

        assertThat(actual).isFalse();
    }

    @DisplayName("열 한 칸, 행 두 칸을 이동한 경우 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveFileOneStepAndRankTwoStep() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.f, Rank.SIX);

        boolean actual = source.isStraightAndDiagonal(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("열 두 칸, 행 한 칸을 이동한 경우 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveFileTwoStepAndRankOneStep() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.c, Rank.FIVE);

        boolean actual = source.isStraightAndDiagonal(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("검정색 폰이 아래로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenBlackPawnMoveDown() {
        PieceColor color = PieceColor.BLACK;
        Square source = Square.of(File.e, Rank.SEVEN);
        Square target = Square.of(File.e, Rank.FIVE);

        boolean actual = source.isNotBackward(target, color);

        assertThat(actual).isTrue();
    }

    @DisplayName("흰색 폰이 위로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenWhitePawnMoveUp() {
        PieceColor color = PieceColor.WHITE;
        Square source = Square.of(File.e, Rank.TWO);
        Square target = Square.of(File.e, Rank.FOUR);

        boolean actual = source.isNotBackward(target, color);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 첫 번째 이동 시 최대 두 칸까지 앞으로 전진할 수 있다.")
    @Test
    void movePawnUpToTwoStepWhenFirstMove() {
        Square source = Square.of(File.e, Rank.TWO);
        Square target = Square.of(File.e, Rank.FOUR);

        boolean actual = source.isOnlyForward(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 두 번째 이동부터는 한 칸씩만 앞으로 전진할 수 있다.")
    @Test
    void movePawnOnlyOneStep() {
        Square source = Square.of(File.e, Rank.THREE);
        Square target = Square.of(File.e, Rank.FOUR);

        boolean actual = source.isOnlyForward(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 대각선에 다른 팀의 기물이 있을 경우 공격할 수 있다.")
    @Test
    void canAttackWhenTargetExistInDiagonal() {
        Square source = Square.of(File.e, Rank.THREE);
        Square target = Square.of(File.f, Rank.FOUR);

        boolean actual = source.isAttack(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 직선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenStraight() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.a, Rank.FOUR);

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.of(File.b, Rank.FOUR),
                Square.of(File.c, Rank.FOUR),
                Square.of(File.d, Rank.FOUR));
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동하는 경우의 경로를 생성한다.")
    @Test
    void createPathWhenDiagonal() {
        Square source = Square.of(File.e, Rank.FOUR);
        Square target = Square.of(File.a, Rank.EIGHT);

        List<Square> actual = source.findPath(target);

        assertThat(actual).containsExactlyInAnyOrder(
                Square.of(File.d, Rank.FIVE),
                Square.of(File.c, Rank.SIX),
                Square.of(File.b, Rank.SEVEN));
    }

    @DisplayName("출발지에서 목적지까지 직선이나 대각선으로 이동하지 않는 경우 경로가 생성되지 않는다.")
    @Test
    void notCreatePathWhenNotStraightAndDiagonal() {
        Square source = Square.of(File.e, Rank.TWO);
        Square target = Square.of(File.d, Rank.FIVE);

        List<Square> actual = source.findPath(target);
        assertThat(actual).isEmpty();
    }
}
