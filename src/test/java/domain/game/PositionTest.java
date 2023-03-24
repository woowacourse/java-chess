package domain.game;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Position은")
class PositionTest {

    @DisplayName("A1, D6의 거리 차이로 (3,5) 를 반환한다.")
    @Test
    void shouldReturnIncrementBetweenSourceAndTargetWhenInputTargetToSource() {
        Position sourcePosition = Position.of(A, ONE);
        Position targetPosition = Position.of(D, SIX);

        Movement increment = sourcePosition.calculateMovement(targetPosition);
        assertThat(increment).isEqualTo(new Movement(3, 5));
    }

    @DisplayName("현재 Position의 rank와 입력 받은 rank의 동등여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO, TWO ,true", "THREE, TWO, false"})
    void shouldReturnIsEqualRankBetweenPositionAndParameterRankWhenInput(Rank sourceRank, Rank targetRank,
                                                                         boolean result) {
        Position position = Position.of(A, sourceRank);
        assertThat(position.hasRankOf(targetRank)).isEqualTo(result);
    }

    @DisplayName("현재 Position의 file과 입력 받은 file의 동등여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"A, A ,true", "B, A, false"})
    void shouldReturnIsEqualFileBetweenPositionAndParameterFileWhenInput(File sourceFile, File targetFile,
                                                                         boolean result) {
        Position position = Position.of(sourceFile, ONE);
        assertThat(position.hasFileOf(targetFile)).isEqualTo(result);
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
