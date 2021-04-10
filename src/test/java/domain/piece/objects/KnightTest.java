package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {
    @DisplayName("knight은 두 칸 전진한 상태에서 좌우로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "c5", "f6", "g5", "c3", "d2", "f2", "g3"})
    void knight_move_if_empty_piece(String endPosition) {
        Knight knight = Knight.of("N", true);
        assertDoesNotThrow(() -> knight.checkMovable(Position.of("e4"), Position.of(endPosition), true));
    }

    @DisplayName("이동하려는 기물(나이)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "c5", "f6", "g5", "c3", "d2", "f2", "g3"})
    void invalid_turn_false_test(String endPosition) {
        Knight knight = Knight.of("N", true);
        assertThatThrownBy(() -> knight.checkMovable(Position.of("e4"), Position.of(endPosition), false))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상대방의 차례입니다.");
    }

    @DisplayName("나이트가 이동하려는 위치가 이동 가능한 범위가 아니면 실패를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "f3", "e5", "e4"})
    void cant_move_knight_test(String endPoint) {
        Knight knight = Knight.of("N", true);
        assertThatThrownBy(() -> knight.checkMovable(Position.of("e4"), Position.of(endPoint), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("N은 선택된 위치로 이동할 수 없습니다.");
    }
}