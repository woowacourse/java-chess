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

    @DisplayName("대각선으로 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        // given
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position targetPositionRightUp = new Position(File.E, Rank.SIX);
        final Position targetPositionRightDown = new Position(File.E, Rank.FOUR);
        final Position targetPositionLeftUP = new Position(File.C, Rank.SIX);
        final Position targetPositionLeftDown = new Position(File.C, Rank.FOUR);

        // when
        final boolean resultRightUp = sourcePosition.isDiagonalWith(targetPositionRightUp);
        final boolean resultRightDown = sourcePosition.isDiagonalWith(targetPositionRightDown);
        final boolean resultLeftUP = sourcePosition.isDiagonalWith(targetPositionLeftUP);
        final boolean resultLeftDown = sourcePosition.isDiagonalWith(targetPositionLeftDown);

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
        final Position sourcePosition = new Position(File.D, Rank.FIVE);
        final Position notMovablePosition = new Position(File.D, Rank.SIX);

        // when
        final boolean result = sourcePosition.isDiagonalWith(notMovablePosition);

        // then
        Assertions.assertThat(result).isFalse();
    }
}
