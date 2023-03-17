package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IndexConverterTest {

    @ParameterizedTest(name = "findNextDirection()은 방향이 {0}일 때 다음 인덱스로 {1}을 반환한다.")
    @CsvSource({
            "NORTH,18", "EAST,11", "SOUTH,2", "WEST,9",
            "NORTH_EAST,19", "NORTH_WEST,17", "SOUTH_EAST,3", "SOUTH_WEST,1"
    })
    void findNextIndex_givenDirectionAndIndex_thenReturnNextIndex(final Direction direction, final int result) {
        final int nextIndex = IndexConverter.findNextIndex(direction, 10);

        assertThat(nextIndex).isEqualTo(result);
    }
}
