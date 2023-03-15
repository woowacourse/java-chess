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

    @DisplayName("source position부터 target position까지 각 기물이 이동할 수 있는 경로에 따라 알맞은 List<position>을 반환한다.")
    @Test
    void shouldReturnPathToTargetPositionWhenMovePerpendicular() {
        Position sourcePosition = Position.of("c", "2");
        Position targetPosition = Position.of("c", "5");

        Movement movement = sourcePosition.calculateMovement(targetPosition);

        List<Position> path = sourcePosition.getPath(targetPosition, movement);
        assertThat(path).containsExactly(Position.of("c", "3"), Position.of("c", "4"));
    }
}