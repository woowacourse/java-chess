package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KingTest {

    @DisplayName("King은 상하좌우 모든 대각선 방향으로 1칸 이동할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"f4", "e3", "d4", "e5", "f5", "f3", "d5", "d3"})
    void king_move(String endPosition) {
        King king = King.of("K", true);
        assertDoesNotThrow(() -> king.checkMovable(Position.of("e4"), Position.of(endPosition), true));
    }

    @DisplayName("이동하려는 기물(킹)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"f4", "e3", "d4", "e5", "f5", "f3", "d5", "d3"})
    void invalid_turn_false_test(String endPosition) {
        King king = King.of("K", true);
        assertThatThrownBy(() -> king.checkMovable(Position.of("e4"), Position.of(endPosition), false))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상대방의 차례입니다.");
    }

    @DisplayName("킹이 이동하려는 위치가 이동 가능한 범위가 아니면 실패를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"f6", "g5", "g3", "f2", "e2", "d2", "c3", "c4", "d6"})
    void invalid_move_position(String endPosition) {
        King king = King.of("K", true);
        assertThatThrownBy(() -> king.checkMovable(Position.of("e4"), Position.of(endPosition), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("K은 선택된 위치로 이동할 수 없습니다.");
    }
}