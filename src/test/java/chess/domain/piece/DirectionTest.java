package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Direction 은")
class DirectionTest {

    @DisplayName("행 좌표 차이과 열 좌표 차이를 통해 올바른 방향을 판단해야 한다.")
    @ParameterizedTest(name = "{index} {displayName} rowDifference={0} columnDifference={1} expected={2}")
    @CsvSource(value = {"0, 2, E", "-2, 0, S", "0, -1, W", "1, 0, N", "1, 1, NE", "-2, 2, SE", "-1, -1, SW",
            "1, -1, NW"})
    void checkDirection(final int rowDifference, final int columnDifference, final Direction expected) {
        final Direction actual = Direction.calculate(rowDifference, columnDifference);
        assertThat(actual).isEqualTo(expected);
    }

}
