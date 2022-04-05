package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionTest {

    @ParameterizedTest
    @CsvSource(value = {"d8, NORTH", "h4, EAST", "a4, WEST", "d1, SOUTH"})
    @DisplayName("직선 관계의 두 포지션 간의 Direction을 얻는다.")
    void getDirectionOfStraight(String to, Direction expected) {
        final Position fromPosition = Position.of("d4");
        final Position toPosition = Position.of(to);
        final Direction direction = Direction.getDirection(fromPosition, toPosition);

        assertThat(direction).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a1, SOUTH_WEST", "a7, NORTH_WEST", "g1, SOUTH_EAST", "h8, NORTH_EAST"})
    @DisplayName("대각선 관계의 두 포지션 간의 Direction을 얻는다.")
    void getDirectionOfDiagonal(String to, Direction expected) {
        final Position fromPosition = Position.of("d4");
        final Position toPosition = Position.of(to);
        final Direction direction = Direction.getDirection(fromPosition, toPosition);

        assertThat(direction).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b3, WWS", "c2, SSW", "e2, SSE", "f3, EES"})
    @DisplayName("나이트 방향의 두 포지션 간의 Direction을 얻는다.")
    void getDirectionOfKnight(String to, Direction expected) {
        final Position fromPosition = Position.of("d4");
        final Position toPosition = Position.of(to);
        final Direction direction = Direction.getDirection(fromPosition, toPosition);

        assertThat(direction).isEqualTo(expected);
    }
}
