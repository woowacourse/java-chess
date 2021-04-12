package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class QueenTest {
    @DisplayName("Queen은 전후좌우, 대각선으로 칸수 제한없이 움직일 수 있다.(빈칸일 경우)")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "e6", "f3", "f5", "c4", "d3", "g4"})
    void queen_move_if_empty(String endPosition) {
        Queen queen = Queen.of("Q", true);
        assertDoesNotThrow(() -> queen.checkMovable(Position.of("e4"), Position.of(endPosition), true));
    }

    @DisplayName("Queen은 전후좌우, 대각선 이외의 위치로 움직일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "f2", "d2", "f6", "c5", "c3", "g5", "g3"})
    void invalid_move_position(String endPosition) {
        Queen queen = Queen.of("Q", true);
        assertThatThrownBy(() -> queen.checkMovable(Position.of("e4"), Position.of(endPosition), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Q은 선택된 위치로 이동할 수 없습니다.");
    }

    @DisplayName("이동하려는 기물(퀸)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "f2", "d2", "f6", "c5", "c3", "g5", "g3"})
    void check_invalid_turn(String endPosition) {
        Queen queen = Queen.of("B", true);
        assertThatThrownBy(() -> queen.checkMovable(Position.of("c5"), Position.of(endPosition), false))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상대방의 차례입니다.");
    }
}