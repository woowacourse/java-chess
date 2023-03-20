package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c1", "c3,c2", "c3,c4", "c3,c5", "c3,c6", "c3,c7",
        "c3,c8", "c3,a3", "c3,b3", "c3,d3", "c3,e3", "c3,f3", "c3,g3", "c3,h3"})
    @DisplayName("Queen은 사방으로 여러 칸 이동이 가능하다.")
    void move_success_like_rook(final String start, final String end) {
        // given
        Queen queen = new Queen(Team.WHITE);

        // when & then
        assertDoesNotThrow(
            () -> queen.canMove(start, end)
        );
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,a1", "c3,b2", "c3,d4", "c3,e5", "c3,f6", "c3,g7",
        "c3,h8", "c3,b4", "c3,a5", "c3,d2", "c3,e1"})
    @DisplayName("Queen은 대각선으로 여러칸 이동이 가능하다.")
    void move_success_like_bishop(final String start, final String end) {
        // given
        Queen queen = new Queen(Team.WHITE);

        // when & then
        assertDoesNotThrow(() -> queen.canMove(start, end));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,b1", "c3,f5", "c3,f7"})
    @DisplayName("Queen은 팔방이 아닌 칸으로 이동하면 에러를 발생시킨다.")
    void throws_exception_when_move_position_invalid(final String start, final String end) {
        // given
        Queen queen = new Queen(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> queen.canMove(start, end)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
