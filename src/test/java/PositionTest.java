import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("source position과 target position간 거리차이를 반환한다.")
    @Test
    void shouldReturnIncrementBetweenSourceAndTargetWhenInputTargetToSource() {
        Position sourcePosition = Position.of("a", "1");
        Position targetPosition = Position.of("d", "6");

        Movement increment = sourcePosition.calculateMovement(targetPosition);
        assertThat(increment).isEqualTo(new Movement(3, 5));
    }

    @DisplayName("현재 position rank와 parameter로 들어온 rank의 동등여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2, TWO ,true", "3, TWO, false"})
    void shouldReturnIsEqualRankBetweenSourcePositionAndParameterRankWhenInput(String sourceRank, Rank targetRank, boolean result) {
        Position position = Position.of("a", sourceRank);
        assertThat(position.hasRankOf(targetRank)).isEqualTo(result);
    }


    @DisplayName("source position부터 target position까지 각 기물이 수직 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveUpPerpendicular() {
        Position sourcePosition = Position.of("c", "2");
        Position targetPosition = Position.of("c", "5");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "3"), Position.of("c", "4"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 수직 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveDownPerpendicular() {
        Position sourcePosition = Position.of("c", "5");
        Position targetPosition = Position.of("c", "2");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "4"), Position.of("c", "3"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 수직 왼쪽 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftPerpendicular() {
        Position sourcePosition = Position.of("d", "5");
        Position targetPosition = Position.of("a", "5");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "5"), Position.of("b", "5"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 수직 오른쪽 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightPerpendicular() {
        Position sourcePosition = Position.of("a", "5");
        Position targetPosition = Position.of("d", "5");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "5"), Position.of("c", "5"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 오른쪽 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightAndUpward() {
        Position sourcePosition = Position.of("a", "1");
        Position targetPosition = Position.of("d", "4");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "2"), Position.of("c", "3"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 오른쪽 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightAndDownward() {
        Position sourcePosition = Position.of("a", "4");
        Position targetPosition = Position.of("d", "1");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "3"), Position.of("c", "2"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 왼쪽 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftAndUpward() {
        Position sourcePosition = Position.of("d", "1");
        Position targetPosition = Position.of("a", "4");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "2"), Position.of("b", "3"));
    }

    @DisplayName("source position부터 target position까지 각 기물이 왼쪽 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftAndDownward() {
        Position sourcePosition = Position.of("d", "4");
        Position targetPosition = Position.of("a", "1");

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "3"), Position.of("b", "2"));
    }
}