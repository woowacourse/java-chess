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
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.SEVEN);

        final boolean actual = source.isStraight(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 가로로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveHorizontal() {
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.a, Rank.FOUR);

        final boolean actual = source.isStraight(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지에서 목적지까지 대각선으로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveDiagonal() {
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.h, Rank.SEVEN);

        final boolean actual = source.isDiagonal(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("출발지부터 목적지까지 한 칸 이내로 이동한 경우 True를 리턴한다.")
    @Test
    void returnTrueWhenMoveWithinOneStep() {
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.FIVE);

        final boolean actual = source.isWithinOneStep(target);

        assertThat(actual).isTrue();
    }


    @DisplayName("출발지부터 목적지까지 한 칸을 초과하여 이동한 경우 False를 리턴한다.")
    @Test
    void returnFalseWhenMoveMoreThanOneStep() {
        final Square source = new Square(File.e, Rank.FOUR);
        final Square target = new Square(File.e, Rank.SEVEN);

        final boolean actual = source.isWithinOneStep(target);

        assertThat(actual).isFalse();
    }

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

    @DisplayName("검정색 폰이 아래로 이동하면 True를 리턴한다.")
    @Test
    void returnTrueWhenBlackPawnMoveDown() {
        final PieceColor color = PieceColor.BLACK;
        final Square source = new Square(File.e, Rank.SEVEN);
        final Square target = new Square(File.e, Rank.FIVE);

        final boolean actual = source.isNotBackward(target, color);

        assertThat(actual).isTrue();
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

    @DisplayName("폰은 첫 번째 이동 시 최대 두 칸까지 앞으로 전진할 수 있다.")
    @Test
    void movePawnUpToTwoStepWhenFirstMove() {
        final Square source = new Square(File.e, Rank.TWO);
        final Square target = new Square(File.e, Rank.FOUR);

        final boolean actual = source.isOnlyForward(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 두 번째 이동부터는 한 칸씩만 앞으로 전진할 수 있다.")
    @Test
    void movePawnOnlyOneStep() {
        final Square source = new Square(File.e, Rank.THREE);
        final Square target = new Square(File.e, Rank.FOUR);

        final boolean actual = source.isOnlyForward(target);

        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 대각선에 다른 팀의 기물이 있을 경우 공격할 수 있다.")
    @Test
    void canAttackWhenTargetExistInDiagonal() {
        final Square source = new Square(File.e, Rank.THREE);
        final Square target = new Square(File.f, Rank.FOUR);

        final boolean actual = source.isAttack(target);

        assertThat(actual).isTrue();
    }

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
