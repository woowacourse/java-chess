package domain.piece.objects;

import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PawnTest {
    @DisplayName("Pawn은 1칸, 2칸, 혹은 대각선 칸으로 이동가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"P:true:a7:a6:true", "P:true:a7:a5:true", "P:true:c6:d5:true", "P:true:c6:d5:true",
            "P:true:c6:b5:true", "p:false:e4:d5:false", "p:false:f4:f6:false"}, delimiter = ':')
    void pawn_move(String name, boolean color, String start, String end, boolean turn) {
        Pawn pawn = Pawn.of(name, color);
        assertDoesNotThrow(() -> pawn.checkMovable(Position.of(start), Position.of(end), turn));
    }

    @DisplayName("이동하려는 기물(폰)의 턴이 아니면 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"P:true:a7:a6:false", "P:true:a7:a5:false", "P:true:c6:d5:false", "P:true:c6:d5:false",
            "P:true:c6:b5:false", "p:false:e4:d5:true", "p:false:f4:f6:true"}, delimiter = ':')
    void invalid_turn_false_test(String name, boolean color, String start, String end, boolean turn) {
        Pawn pawn = Pawn.of(name, color);
        assertThatThrownBy(() -> pawn.checkMovable(Position.of(start), Position.of(end), turn))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상대방의 차례입니다.");
    }

    @DisplayName("폰이 이동하려는 위치가 이동 가능한 범위가 아니면 실패를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"P:true:e4:d5:true", "P:true:e4:e6:true", "p:false:e4:e3:false", "p:false:e4:f3:false"}, delimiter = ':')
    void invalid_move_position(String name, boolean color, String start, String end, boolean turn) {
        Pawn pawn = Pawn.of(name, color);
        assertThatThrownBy(() -> pawn.checkMovable(Position.of(start), Position.of(end), turn))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
    }
}