package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
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

    @DisplayName("한 포지션에서 다른 포지션까지의 위치 차의 제곱을 계산할 수 있다")
    @Test
    void should_ReturnSquaredDistanceBetweenPositions() {
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(2, 3);

        assertThat(position1.squaredDistanceWith(position2)).isEqualTo(5);
    }

    @DisplayName("다른 포지션으로의 위 아래 방향을 계산할 수 있다.")
    @Test
    void should_CalculateDirectionToTargetPosition() {
        Position higherPosition = Position.of(0, 0);
        Position lowerPosition = Position.of(1, 0);

        assertAll(
                () -> assertThat(lowerPosition.directionTo(higherPosition)).isEqualTo(Direction.UP),
                () -> assertThat(higherPosition.directionTo(lowerPosition)).isEqualTo(Direction.DOWN)
        );
    }

    @DisplayName("포지션이 같은 행에 있는지 확인할 수 있다")
    @Test
    void should_CheckSameRowToTarget() {
        Position position = Position.of(0, 0);
        RowPosition rowPosition = new RowPosition(0);

        assertThat(position.rowIs(rowPosition)).isTrue();
    }

    @DisplayName("대각선 상의 포지션끼리 경유 경로를 구할 수 있다")
    @Test
    void should_ReturnDiagonalPassingPath() {
        Position start = Position.of(0, 0);
        Position destination = Position.of(2, 2);
        List<Position> path = start.diagonalPath(destination);

        assertThat(path).contains(Position.of(1, 1));
    }

    @DisplayName("직선 상의 포지션끼리 경유 경로를 구할 수 있다")
    @Test
    void should_ReturnStraightPassingPath() {
        Position start = Position.of(0, 0);
        Position destination = Position.of(0, 2);
        List<Position> path = start.straightPath(destination);

        assertThat(path).contains(Position.of(0, 1));
    }


}
