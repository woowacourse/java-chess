package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @ParameterizedTest(name = "match()는 Direction.{0}이고 rank={1}, file={2}이면 {3}을 반환한다.")
    @CsvSource({"SOUTH,0,-1,true", "NORTH_WEST,-3,3,true", "NORTH_WEST_WEST,-4,2,true"})
    void match_givenRankAndFile_thenReturnResult(
            final Direction direction,
            final int rank,
            final int file,
            final boolean result
    ) {
        assertThat(direction.match(rank, file)).isEqualTo(result);
    }

    @ParameterizedTest(name = "findDirection()은 rank = {0}, file = {1}일 때 Direction.{2}를 반환한다.")
    @CsvSource({
            "0,1,NORTH",
            "1,0,EAST",
            "0,-1,SOUTH",
            "-1,0,WEST",
            "1,1,NORTH_EAST",
            "-1,1,NORTH_WEST",
            "1,-1,SOUTH_EAST",
            "-1,-1,SOUTH_WEST",
            "1,2,NORTH_NORTH_EAST",
            "-1,2,NORTH_NORTH_WEST",
            "1,-2,SOUTH_SOUTH_EAST",
            "-1,-2,SOUTH_SOUTH_WEST",
            "-2,1,NORTH_WEST_WEST",
            "2,1,NORTH_EAST_EAST",
            "-2,-1,SOUTH_WEST_WEST",
            "2,-1,SOUTH_EAST_EAST"
    })
    void findDirection_givenRankAndFile_thenReturnDirection(final int rank, final int file,
            final Direction result) {
        final Direction direction = Direction.findDirection(file, rank);

        assertThat(direction).isEqualTo(result);
    }
}
