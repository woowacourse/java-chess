package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BishopTest {

    @DisplayName("이동 방향이 대각선이고, 목적지가 빈칸인 경 목적지로 이동한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a7", "f8", "b4", "e3"})
    void check_diagonal_if_empty_piece(String endPosition) {
        Bishop bishop = Bishop.of("B", true);
        assertDoesNotThrow(() -> bishop.checkMovable(Position.of("c5"), Position.of(endPosition), true));
    }

    @DisplayName("이동하려는 기물(비숍)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c6", "a6", "e5", "h4", "c3"})
    void check_diagonal_false_test(String endPosition) {
        Bishop bishop = Bishop.of("B", true);
        assertThatThrownBy(() -> bishop.checkMovable(Position.of("c5"), Position.of(endPosition), false))
                .isInstanceOf(RuntimeException.class).hasMessage("상대방의 차례입니다.");
    }

    @DisplayName("비숍이 이동하려는 위치가 대각선이 아니면 실패를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c6", "a6", "e5", "h4", "c3"})
    void invalid_move_position(String endPosition) {
        Bishop bishop = Bishop.of("B", true);
        assertThatThrownBy(() -> bishop.checkMovable(Position.of("c5"), Position.of(endPosition), true))
                .isInstanceOf(RuntimeException.class).hasMessage("B은 선택된 위치로 이동할 수 없습니다.");
    }
}