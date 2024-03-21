package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("file, rank를 입력받아 위치를 생성한다.")
    @Test
    void create() {
        // given
        final Position positionA_ONE = new Position(File.A, Rank.ONE);
        final Position positionB_TWO = new Position(File.B, Rank.TWO);

        // when && then
        Assertions.assertThat(positionA_ONE).isEqualTo(new Position(File.A, Rank.ONE));
        Assertions.assertThat(positionB_TWO).isEqualTo(new Position(File.B, Rank.TWO));
    }

    @DisplayName("직선(상하좌우)으로 이동할 수 있다.")
    @Test
    void canMoveStraight() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position targetPositionUp = new Position(File.D, Rank.EIGHT);
        final Position targetPositionDown = new Position(File.D, Rank.ONE);
        final Position targetPositionLeft = new Position(File.A, Rank.FIVE);
        final Position targetPositionRight = new Position(File.H, Rank.FIVE);

        // when
        final boolean resultUp = currentPosition.isStraightWith(targetPositionUp);
        final boolean resultDown = currentPosition.isStraightWith(targetPositionDown);
        final boolean resultLeft = currentPosition.isStraightWith(targetPositionLeft);
        final boolean resultRight = currentPosition.isStraightWith(targetPositionRight);

        // then
        Assertions.assertThat(resultUp).isTrue();
        Assertions.assertThat(resultDown).isTrue();
        Assertions.assertThat(resultLeft).isTrue();
        Assertions.assertThat(resultRight).isTrue();
    }

    @DisplayName("직선(상하좌우)으로 이동할 수 없다.")
    @Test
    void canNotMoveStraight() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position notMovablePosition = new Position(File.A, Rank.ONE);

        // when
        final boolean result = currentPosition.isStraightWith(notMovablePosition);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("대각선으로 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position targetPositionRightUp = new Position(File.E, Rank.SIX);
        final Position targetPositionRightDown = new Position(File.E, Rank.FOUR);
        final Position targetPositionLeftUP = new Position(File.C, Rank.SIX);
        final Position targetPositionLeftDown = new Position(File.C, Rank.FOUR);

        // when
        final boolean resultRightUp = currentPosition.isDiagonalWith(targetPositionRightUp);
        final boolean resultRightDown = currentPosition.isDiagonalWith(targetPositionRightDown);
        final boolean resultLeftUP = currentPosition.isDiagonalWith(targetPositionLeftUP);
        final boolean resultLeftDown = currentPosition.isDiagonalWith(targetPositionLeftDown);

        // then
        Assertions.assertThat(resultRightUp).isTrue();
        Assertions.assertThat(resultRightDown).isTrue();
        Assertions.assertThat(resultLeftUP).isTrue();
        Assertions.assertThat(resultLeftDown).isTrue();
    }

    @DisplayName("대각선으로 이동할 수 없다.")
    @Test
    void canNotMoveDiagonal() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position notMovablePosition = new Position(File.D, Rank.SIX);

        // when
        final boolean result = currentPosition.isDiagonalWith(notMovablePosition);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("수직상의 거리차이가 같다.")
    @Test
    void isSameVerticalDifference() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.D, Rank.SEVEN);

        // when
        final boolean result = currentPosition.isVerticalWithDistance(otherPosition, 2);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("수직상의 거리차이가 다르다.")
    @Test
    void isNotSameVerticalDifference() { // TODO: 거리 안맞는 경우 + 수직이 아닌 경우들도 추가하기
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.D, Rank.SIX);

        // when
        final boolean result = currentPosition.isVerticalWithDistance(otherPosition, 2);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("수평상의 거리차이가 같다.")
    @Test
    void isSameHorizontalDifference() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.B, Rank.FIVE);

        // when
        final boolean result = currentPosition.isHorizontalWithDistance(otherPosition, 2);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("수평상의 거리차이가 다르다.")
    @Test
    void isNotSameHorizontalDifference() { // TODO: 거리 안맞는 경우 + 수직이 아닌 경우들도 추가하기
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.A, Rank.FIVE);

        // when
        final boolean result = currentPosition.isHorizontalWithDistance(otherPosition, 2);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("대각선상의 거리차이가 같다.")
    @Test
    void isSameDiagonalDifference() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.B, Rank.SEVEN);

        // when
        final boolean result = currentPosition.isDiagonalWithDistance(otherPosition, 2);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("대각선상의 거리차이가 같지 않다.")
    @Test
    void isNotSameDiagonalDifference() {
        // given
        final Position currentPosition = new Position(File.D, Rank.FIVE);
        final Position otherPosition = new Position(File.B, Rank.SEVEN);

        // when
        final boolean result = currentPosition.isDiagonalWithDistance(otherPosition, 1);

        // then
        Assertions.assertThat(result).isFalse();
    }
}
