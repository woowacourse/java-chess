package chess.model.position;

import static chess.model.board.PositionFixture.A1;
import static chess.model.board.PositionFixture.B2;
import static chess.model.board.PositionFixture.D4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("of()는 체스 판의 위치를 관리하는 객체를 생성한다.")
    void constructor_givenRankAndFile_thenSuccess() {
        final Position position = assertDoesNotThrow(() -> Position.of(File.A, Rank.FIRST));

        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    @DisplayName("differ()는 다른 Position을 건네주면 각 Position의 거리를 반환한다.")
    void differ_givenOtherPosition_thenReturnDistance() {
        // when
        final Distance result = A1.differ(B2);

        // then
        assertThat(result).extracting("rank").isEqualTo(-1);
        assertThat(result).extracting("file").isEqualTo(-1);
    }

    @Nested
    @DisplayName("findNextPosition() 테스트")
    class FindNextPositionMethodTest {

        @ParameterizedTest(name = " D4에서 Direction.{0}를 건네주면 ({1} / {2})의 Position을 반환한다")
        @DisplayName("findNextPosition() 성공 테스트")
        @CsvSource(value = {
                "NORTH:D:FIFTH", "EAST:E:FOURTH", "SOUTH:D:THIRD", "WEST:C:FOURTH", "NORTH_EAST:E:FIFTH",
                "NORTH_WEST:C:FIFTH", "SOUTH_EAST:E:THIRD", "SOUTH_WEST:C:THIRD", "NORTH_NORTH_EAST:E:SIXTH",
                "NORTH_NORTH_WEST:C:SIXTH", "SOUTH_SOUTH_EAST:E:SECOND", "SOUTH_SOUTH_WEST:C:SECOND",
                "NORTH_WEST_WEST:B:FIFTH", "NORTH_EAST_EAST:F:FIFTH", "SOUTH_WEST_WEST:B:THIRD",
                "SOUTH_EAST_EAST:F:THIRD"
        }, delimiter = ':')
        void findPosition_givenValidDirection_thenSuccess(
                final Direction direction,
                final File expectedFile,
                final Rank expectedRank
        ) {
            // when
            final Position actual = D4.findNextPosition(direction);

            // then
            final Position expected = Position.of(expectedFile, expectedRank);

            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "({1} / {2})의 Position Direction.{0}을 건네주면 예외가 발생한다")
        @DisplayName("findNextPosition() 실패 테스트")
        @CsvSource(value = {
                "NORTH:D:EIGHTH", "EAST:H:FOURTH", "SOUTH:D:FIRST", "WEST:A:FIRST", "NORTH_EAST:D:EIGHTH",
                "NORTH_EAST:H:FOURTH", "NORTH_WEST:A:FOURTH", "NORTH_WEST:D:EIGHTH", "NORTH_WEST:A:FOURTH",
                "NORTH_WEST:D:EIGHTH", "SOUTH_EAST:H:FOURTH", "SOUTH_EAST:D:FIRST", "SOUTH_WEST:A:FOURTH",
                "SOUTH_WEST:D:FIRST"
        }, delimiter = ':')
        void findPosition_givenInValidDirection_thenFail(
                final Direction direction,
                final File givenFile,
                final Rank givenRank
        ) {
            // given
            final Position position = Position.of(givenFile, givenRank);

            // when, then
            assertThatThrownBy(() -> position.findNextPosition(direction))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
