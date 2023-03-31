package chess.domain;

import static chess.domain.Direction.EAST;
import static chess.domain.Direction.EAST_EAST_NORTH;
import static chess.domain.Direction.EAST_EAST_SOUTH;
import static chess.domain.Direction.EAST_NORTH_NORTH;
import static chess.domain.Direction.EAST_SOUTH_SOUTH;
import static chess.domain.Direction.NORTH;
import static chess.domain.Direction.NORTH_EAST;
import static chess.domain.Direction.NORTH_WEST;
import static chess.domain.Direction.SOUTH;
import static chess.domain.Direction.SOUTH_EAST;
import static chess.domain.Direction.SOUTH_WEST;
import static chess.domain.Direction.WEST;
import static chess.domain.Direction.WEST_NORTH_NORTH;
import static chess.domain.Direction.WEST_SOUTH_SOUTH;
import static chess.domain.Direction.WEST_WEST_NORTH;
import static chess.domain.Direction.WEST_WEST_SOUTH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

    private static final int startFile = 3;
    private static final int startRank = 3;
    private static final Position START_POSITION = Position.of(startFile, startRank);

    private static Stream<Arguments> provideDisplacementsAndExpected() {
        return Stream.of(
                Arguments.of(1, 0, EAST),
                Arguments.of(-1, 0, WEST),
                Arguments.of(0, -1, SOUTH),
                Arguments.of(0, 1, NORTH),
                Arguments.of(1, -1, SOUTH_EAST),
                Arguments.of(1, 1, NORTH_EAST),
                Arguments.of(-1, 1, NORTH_WEST),
                Arguments.of(-1, -1, SOUTH_WEST),
                Arguments.of(2, 1, EAST_EAST_NORTH),
                Arguments.of(2, -1, EAST_EAST_SOUTH),
                Arguments.of(-2, 1, WEST_WEST_NORTH),
                Arguments.of(-2, -1, WEST_WEST_SOUTH),
                Arguments.of(1, 2, EAST_NORTH_NORTH),
                Arguments.of(-1, 2, WEST_NORTH_NORTH),
                Arguments.of(1, -2, EAST_SOUTH_SOUTH),
                Arguments.of(-1, -2, WEST_SOUTH_SOUTH)
        );
    }

    @DisplayName("방향을 조회할 두 위치값이 단위 이동 관계가 아니면 빈 옵셔널을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "2:0", "0:2", "-2:0", "0:-2", "5:0", "0:5", "5:5"}, delimiter = ':')
    void 방향_조회_단위이동아님_예외(int fileDisplacement, int rankDisplacement) {
        Position wrong = Position.of(startFile + fileDisplacement, startRank + rankDisplacement);

        Optional<Direction> found = Direction.find(START_POSITION, wrong);

        assertThat(found.isEmpty()).isTrue();
    }

    @DisplayName("방향을 조회할 두 위치값이 단위 이동 관계이면 해당하는 방향을 반환한다.")
    @ParameterizedTest
    @MethodSource("provideDisplacementsAndExpected")
    void 방향_조회(int fileDisplacement, int rankDisplacement, Direction expected) {
        Optional<Direction> found = Direction.find(START_POSITION,
                Position.of(startFile + fileDisplacement, startRank + rankDisplacement));

        assertThat(found.get()).isEqualTo(expected);
    }

}
