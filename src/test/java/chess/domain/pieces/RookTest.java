package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c1", "c3,c2", "c3,c4", "c3,c5", "c3,c6", "c3,c7", "c3,c8",
        "c3,a3", "c3,b3", "c3,d3", "c3,e3", "c3,f3", "c3,g3", "c3,h3"})
    @DisplayName("Rook은 사방으로 여러칸 이동이 가능하다.")
    void move_success(final String start, final String end) {
        // given
        Rook rook = new Rook(Team.WHITE);

        // when & then
        assertDoesNotThrow(
            () -> rook.canMove(start, end)
        );
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,b1", "c3,b2", "c3,f5", "c3,f7"})
    @DisplayName("Rook이 사방이 아닌 다른 곳으로 이동하면 예외를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String start, final String end) {
        // given
        Rook rook = new Rook(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> rook.canMove(start, end)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
