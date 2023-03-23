package chess.model.position;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.B2;
import static chess.helper.PositionFixture.D4;
import static chess.model.position.File.A;
import static chess.model.position.Rank.FIRST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @Test
    @DisplayName("생성자는 File과 Rank를 주면 Position을 생성한다")
    void constructor_givenFileAndRank_thenReturnPosition() {
        // when
        final Position actual = new Position(A, FIRST);

        // then
        assertThat(actual).isEqualTo(A1);
    }

    @Test
    @DisplayName("of()는 체스 판의 위치를 관리하는 객체를 생성한다.")
    void constructor_givenRankAndFile_thenSuccess() {
        final Position position = assertDoesNotThrow(() -> Position.of(A, FIRST));

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

        @ParameterizedTest(name = " D4에서 Direction.{0}를 건네주면 다음 Position을 반환한다")
        @DisplayName("findNextPosition() 성공 테스트")
        @MethodSource("chess.helper.arguments.PositionArguments#provideValidFindNextPositionArguments")
        void findPosition_givenValidDirection_thenSuccess(
                final Direction direction,
                final Position expected
        ) {
            // when
            final Position actual = D4.findNextPosition(direction);

            // then
            assertThat(actual).isSameAs(expected);
        }

        @ParameterizedTest(name = "체스 판의 범위를 넘어서는 Direction.{0}을 건네주면 예외가 발생한다")
        @DisplayName("findNextPosition() 실패 테스트")
        @MethodSource("chess.helper.arguments.PositionArguments#provideInvalidFindNextPositionArguments")
        void findPosition_givenInValidDirection_thenFail(final Direction direction, final Position position) {
            // when, then
            assertThatThrownBy(() -> position.findNextPosition(direction))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
