package chess.domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @DisplayName("포지션은 x축 대칭인 포지션을 반환할 수 있다")
    @Test
    void should_ReturnVerticalReversePosition() {
        Position testPosition = Position.of(0, 0);
        assertThat(testPosition.verticalReversePosition()).isEqualTo(Position.of(7, 0));
    }

    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 직선인지 확인할 수 있다")
    @Test
    void should_CheckVerticalRelationShipWithPositions() {
        Position position = Position.of(1, 1);
        assertAll(
                () -> assertThat(position.isStraightWith(Position.of(2, 1))).isTrue(),
                () -> assertThat(position.isStraightWith(Position.of(1, 2))).isTrue(),
                () -> assertThat(position.isStraightWith(Position.of(4, 4))).isFalse()
        );
    }

    @DisplayName("한 포지션에서 다른 포지션까지의 연결이 대각인지 확인할 수 있다")
    @Test
    void should_CheckDiagonalRelationShipWithPositions() {
        Position position = Position.of(1, 1);
        assertAll(
                () -> assertThat(position.isDiagonalWith(Position.of(2, 2))).isTrue(),
                () -> assertThat(position.isDiagonalWith(Position.of(2, 1))).isFalse()
        );
    }
}
