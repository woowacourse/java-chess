package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {
    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위라면, 성공을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "d4", "c5", "e5"})
    void check_row_column_range_true_test(String endPosition) {
        Rook rook = Rook.of("R", true);
        assertDoesNotThrow(() -> rook.checkMovable(Position.of("d5"), Position.of(endPosition), true));
    }

    @DisplayName("룩이 이동하려는 위치가 상하좌우 범위가 아니라면, 실패를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"e4", "c6"})
    void check_row_column_range_false_test(String end) {
        Rook rook = Rook.of("R", true);
        assertThatThrownBy(() -> rook.checkMovable(Position.of("d5"), Position.of(end), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("R은 선택된 위치로 이동할 수 없습니다.");
    }

    @DisplayName("이동하려는 기물(룩)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "d4", "c5", "e5"})
    void check_invalid_turn(String endPosition) {
        Rook rook = Rook.of("R", true);
        assertThatThrownBy(() -> rook.checkMovable(Position.of("d5"), Position.of(endPosition), false))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상대방의 차례입니다.");
    }
}