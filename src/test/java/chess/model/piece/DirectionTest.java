package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest(name = "Direction.{0}이고 rank={1}, file={2}이면 {3}이 반환된다.")
    @CsvSource({"SOUTH,0,-1,true", "NORTH_WEST,-3,3,true", "NORTH_WEST_WEST,-4,2,true"})
    void match_givenRankAndFile_thenReturnResult(
            final Direction direction,
            final int rank,
            final int file,
            final boolean result
    ) {
        assertThat(direction.match(rank, file)).isEqualTo(result);
    }
}
