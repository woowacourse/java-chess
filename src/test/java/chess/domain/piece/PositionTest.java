package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("file, rank를 입력받아 위치를 생성한다.")
    @Test
    void create() {
        // given
        final Position positionA_ONE = new Position('a', 1);
        final Position positionB_TWO = new Position('b', 2);

        // when && then
        Assertions.assertThat(positionA_ONE).isEqualTo(new Position(File.A, Rank.ONE));
        Assertions.assertThat(positionB_TWO).isEqualTo(new Position(File.B, Rank.TWO));
    }

    @DisplayName("직선(상하좌우)으로 이동할 수 있다.")
    @Test
    void canMoveStraight() {
        // given
        final Position currentPosition = new Position('d', 5);
        final Position targetPositionUp = new Position('d', 8);
        final Position targetPositionDown = new Position('d', 1);
        final Position targetPositionLeft = new Position('a', 5);
        final Position targetPositionRight = new Position('h', 5);

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
        final Position currentPosition = new Position('d', 5);
        final Position notMovablePosition = new Position('a', 1);

        // when
        final boolean result = currentPosition.isStraightWith(notMovablePosition);

        // then
        Assertions.assertThat(result).isFalse();
    }
}
