package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Position 테스트")
class PositionTest {

    @DisplayName("좌표값을 입력해 위치를 지정할 떄 올바른 위치가 들어오면 인스턴스를 생성한다")
    @ParameterizedTest(name = "{index} {displayName} row={0} column={1}")
    @CsvSource(value = {"FIRST, A", "EIGHTH, H", "FIRST, H", "EIGHTH, A"})
    void valid_Position(final Row row, final Column column) {
        assertThatNoException().isThrownBy(() -> new Position(column, row));
    }

    @DisplayName("두 위치 간의 행 차이를 계산한다.")
    @Test
    void calculate_Row() {
        Position current = CachedPosition.a1;
        Position target = CachedPosition.a2;
        int actual = current.calculateRowDifference(target);

        assertThat(actual).isEqualTo(-1);
    }

    @DisplayName("두 위치 간의 열 차이를 계산한다.")
    @Test
    void calculate_Column() {
        Position current = CachedPosition.a1;
        Position target = CachedPosition.b1;
        int actual = current.calculateColumnDifference(target);

        assertThat(actual).isEqualTo(-1);
    }

    @DisplayName("방향이 정해졌을 때 ")
    @Nested
    class MovingTest {
        @Test
        @DisplayName("체스판의 범위를 벗어났으면 예외를 반환한다")
        void outOfBound() {
            Position current = CachedPosition.a1;
            assertThatThrownBy(
                    () -> current.move(Direction.SW)
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바르지 않은 위치입니다.");

        }

        @Test
        @DisplayName("체스판의 범위내면 위치로 이동한다")
        void successMove() {
            Position current = CachedPosition.a1;
            final Position expected = CachedPosition.a2;

            assertThat(current.move(Direction.N)).isEqualTo(expected);

        }
    }
}
