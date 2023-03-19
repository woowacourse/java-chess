package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.piece.Direction;
import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DistanceTest {

    @Test
    @DisplayName("생성자는 rank와 file을 전달하면 Distance를 생성한다.")
    void constructor_givenRankAndFile_ThenSuccess() {
        final Distance distance = assertDoesNotThrow(() -> new Distance(1, 1));

        assertThat(distance).isExactlyInstanceOf(Distance.class);
    }

    @ParameterizedTest(name = "matchByDirection()은 ({0} / {1})일 때 Direction.{2}라면 true를 반환한다")
    @DisplayName("matchByDirection() 테스트")
    @CsvSource(value = {
            "0:1:NORTH", "1:0:EAST", "0:-1:SOUTH", "-1:0:WEST", "1:1:NORTH_EAST", "-1:1:NORTH_WEST",
            "1:-1:SOUTH_EAST", "-1:-1:SOUTH_WEST", "1:2:NORTH_NORTH_EAST", "-1:2:NORTH_NORTH_WEST",
            "1:-2:SOUTH_SOUTH_EAST", "-1:-2:SOUTH_SOUTH_WEST", "-2:1:NORTH_WEST_WEST", "2:1:NORTH_EAST_EAST",
            "-2:-1:SOUTH_WEST_WEST", "2:-1:SOUTH_EAST_EAST"
    }, delimiter = ':')
    void matchByDirection_givenRankAndFile_thenReturnTrue(final int file, final int rank,
            final Direction direction) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean actual = distance.matchByDirection(direction);

        // then
        assertThat(actual).isTrue();
    }
}
