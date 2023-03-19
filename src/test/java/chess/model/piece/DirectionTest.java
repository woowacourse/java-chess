package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @Nested
    @DisplayName("findDirection() 테스트")
    class FindDirectionMethodTest {

        @Test
        @DisplayName("유효한 rank, file을 전달하면 그 방향에 맞는 Direction을 반환한다")
        void findDirection_givenValidRankAndFile_thenSuccess() {
            // when
            final Direction actual = Direction.findDirection(3, 3);

            // then
            assertThat(actual).isSameAs(Direction.NORTH_EAST);
        }

        @Test
        @DisplayName("유효하지 않은 rank, file을 전달하면 예외가 발생한다")
        void findDirection_gievnInvalidRankAndFile_thenFail() {
            assertThatThrownBy(() -> Direction.findDirection(5, 7))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("방향을 찾을 수 없습니다.");
        }
    }

    @ParameterizedTest(name = "match()는 Direction.{0}이고 rank={1}, file={2}이면 {3}을 반환한다.")
    @DisplayName("match() 테스트")
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
    @DisplayName("findDirection() 테스트")
    @CsvSource({
            "0,1,NORTH", "1,0,EAST", "0,-1,SOUTH", "-1,0,WEST", "1,1,NORTH_EAST", "-1,1,NORTH_WEST",
            "1,-1,SOUTH_EAST", "-1,-1,SOUTH_WEST", "1,2,NORTH_NORTH_EAST", "-1,2,NORTH_NORTH_WEST",
            "1,-2,SOUTH_SOUTH_EAST", "-1,-2,SOUTH_SOUTH_WEST", "-2,1,NORTH_WEST_WEST", "2,1,NORTH_EAST_EAST",
            "-2,-1,SOUTH_WEST_WEST", "2,-1,SOUTH_EAST_EAST"
    })
    void findDirection_givenRankAndFile_thenReturnDirection(final int rank, final int file, final Direction result) {
        final Direction direction = Direction.findDirection(file, rank);

        assertThat(direction).isEqualTo(result);
    }

    @Nested
    @DisplayName("findNextRank() 테스트")
    class FindNextRankMethodTest {

        @ParameterizedTest(name = "Rank.FIFTH일 때 Direction.{0}에서 findNextRank()를 호출하면 {1}을 반환한다")
        @DisplayName("findNextRank() 성공 테스트")
        @CsvSource(value = {
                "NORTH:SIXTH", "EAST:FIFTH", "SOUTH:FOURTH", "WEST:FIFTH", "SOUTH_SOUTH_WEST:THIRD",
                "NORTH_EAST:SIXTH", "NORTH_WEST:SIXTH", "SOUTH_EAST:FOURTH", "SOUTH_WEST:FOURTH",
                "NORTH_NORTH_EAST:SEVENTH", "NORTH_NORTH_WEST:SEVENTH", "SOUTH_SOUTH_EAST:THIRD",
                "NORTH_WEST_WEST:SIXTH", "NORTH_EAST_EAST:SIXTH", "SOUTH_WEST_WEST:FOURTH", "SOUTH_EAST_EAST:FOURTH"
        }, delimiter = ':')
        void findNextRank_givenOffset_thenReturnNextRank(final Direction direction, final Rank expected) {
            // given
            final Rank fifth = Rank.FIFTH;

            // when
            final Rank actual = direction.findNextRank(fifth);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "Direction.{1}일 때 Rank가 {0}이라면 계산 결과가 1 ~ 8의 범위를 넘어서서 예외가 발생한다.")
        @DisplayName("findNextRank() 실패 테스트")
        @CsvSource(value = {
                "FIRST:SOUTH", "FIRST:SOUTH_EAST", "FIRST:SOUTH_WEST", "FIRST:SOUTH_SOUTH_EAST",
                "FIRST:SOUTH_SOUTH_WEST", "FIRST:SOUTH_WEST_WEST", "FIRST:SOUTH_EAST_EAST", "EIGHTH:NORTH:",
                "EIGHTH:NORTH_EAST", "EIGHTH:NORTH_WEST", "EIGHTH:NORTH_NORTH_EAST", "EIGHTH:NORTH_NORTH_WEST",
                "EIGHTH:NORTH_WEST_WEST", "EIGHTH:NORTH_EAST_EAST"
        }, delimiter = ':')
        void findNextRank_whenBoundaryRank_givenBoundaryOffset_thenFail(final Rank rank, final Direction direction) {
            assertThatThrownBy(() -> direction.findNextRank(rank))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 행입니다.");
        }
    }

    @Nested
    @DisplayName("findNextFile() 테스트")
    class FindNextFileMethodTest {

        @ParameterizedTest(name = "File.E일 때 Direction.{0}에서 findNextFile()를 호출하면 {1}을 반환한다")
        @DisplayName("findNextFile() 성공 테스트")
        @CsvSource(value = {
                "NORTH:E", "EAST:F", "SOUTH:E", "WEST:D",
                "NORTH_EAST:F", "NORTH_WEST:D", "SOUTH_EAST:F", "SOUTH_WEST:D",
                "NORTH_NORTH_EAST:F", "NORTH_NORTH_WEST:D", "SOUTH_SOUTH_EAST:F", "SOUTH_SOUTH_WEST:D",
                "NORTH_WEST_WEST:C", "NORTH_EAST_EAST:G", "SOUTH_WEST_WEST:C", "SOUTH_EAST_EAST:G"
        }, delimiter = ':')
        void findNextFile_givenOffset_thenReturnNextRank(final Direction direction, final File expected) {
            // given
            final File e = File.E;

            // when
            final File actual = direction.findNextFile(e);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "Direction.{1}일 때 File이 {0}이라면 계산 결과가 1 ~ 8의 범위를 넘어서서 예외가 발생한다.")
        @DisplayName("findNextFile() 실패 테스트")
        @CsvSource(value = {
                "A:WEST", "A:NORTH_WEST", "A:SOUTH_WEST", "A:NORTH_NORTH_WEST", "A:SOUTH_SOUTH_WEST",
                "A:NORTH_WEST_WEST", "A:SOUTH_WEST_WEST", "H:EAST", "H:NORTH_EAST", "H:SOUTH_EAST",
                "H:NORTH_NORTH_EAST", "H:SOUTH_SOUTH_EAST", "H:NORTH_EAST_EAST", "H:SOUTH_EAST_EAST"
        }, delimiter = ':')
        void findNextFile_whenBoundaryRank_givenBoundaryOffset_thenFail(final File file, final Direction direction) {
            assertThatThrownBy(() -> direction.findNextFile(file))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("존재하지 않는 열입니다.");
        }
    }
}
