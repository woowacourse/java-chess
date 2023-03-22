package domain.piece;

import static domain.piece.File.A;
import static domain.piece.File.B;
import static domain.piece.File.C;
import static domain.piece.File.D;
import static domain.piece.Rank.FIVE;
import static domain.piece.Rank.FOUR;
import static domain.piece.Rank.ONE;
import static domain.piece.Rank.SIX;
import static domain.piece.Rank.THREE;
import static domain.piece.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import controller.mapper.RankMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @DisplayName("Source position과 Target position간 거리차이를 반환한다.")
    @Test
    void shouldReturnIncrementBetweenSourceAndTargetWhenInputTargetToSource() {
        Position sourcePosition = Position.of(A, ONE);
        Position targetPosition = Position.of(D, SIX);

        Movement increment = sourcePosition.calculateMovement(targetPosition);
        assertThat(increment).isEqualTo(new Movement(3, 5));
    }

    @DisplayName("현재 position rank와 parameter로 들어온 rank의 동등여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2, TWO ,true", "3, TWO, false"})
    void shouldReturnIsEqualRankBetweenSourcePositionAndParameterRankWhenInput(String sourceRank, Rank targetRank,
                                                                               boolean result) {
        Position position = Position.of(A, RankMapper.convertTextToRank(sourceRank));
        assertThat(position.hasRankOf(targetRank)).isEqualTo(result);
    }


    @DisplayName("Source position부터 Target position까지 각 기물이 수직 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveUpPerpendicular() {
        Position sourcePosition = Position.of(C, TWO);
        Position targetPosition = Position.of(C, FIVE);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, THREE), Position.of(C, FOUR));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 수직 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveDownPerpendicular() {
        Position sourcePosition = Position.of(C, FIVE);
        Position targetPosition = Position.of(C, TWO);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, FOUR), Position.of(C, THREE));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 수직 왼쪽 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftPerpendicular() {
        Position sourcePosition = Position.of(D, FIVE);
        Position targetPosition = Position.of(A, FIVE);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, FIVE), Position.of(B, FIVE));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 수직 오른쪽 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightPerpendicular() {
        Position sourcePosition = Position.of(A, FIVE);
        Position targetPosition = Position.of(D, FIVE);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(B, FIVE), Position.of(C, FIVE));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 오른쪽 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightAndUpward() {
        Position sourcePosition = Position.of(A, ONE);
        Position targetPosition = Position.of(D, FOUR);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(B, TWO), Position.of(C, THREE));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 오른쪽 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveRightAndDownward() {
        Position sourcePosition = Position.of(A, FOUR);
        Position targetPosition = Position.of(D, ONE);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(B, THREE), Position.of(C, TWO));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 왼쪽 위 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftAndUpward() {
        Position sourcePosition = Position.of(D, ONE);
        Position targetPosition = Position.of(A, FOUR);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, TWO), Position.of(B, THREE));
    }

    @DisplayName("Source position부터 Target position까지 각 기물이 왼쪽 아래 방향으로 이동하는 경로를 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMoveLeftAndDownward() {
        Position sourcePosition = Position.of(D, FOUR);
        Position targetPosition = Position.of(A, ONE);

        List<Position> path = sourcePosition.getPath(targetPosition);
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, THREE), Position.of(B, TWO));
    }
}
