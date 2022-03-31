package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("룩이 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "e6", "f5", "e4", "c5", "e7", "g5", "e3"})
    void checkMovingRange(String to) {
        Piece rook = new Rook(Color.WHITE);

        assertThatCode(() -> rook.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("룩이 이동할 수 없는 범위로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"e5", "c5", "c3", "e3"})
    void checkMovingRangeException(String toPosition) {
        Piece rook = new Rook(Color.WHITE);

        assertThatThrownBy(() -> rook.checkMovingRange(emptyBoard, Position.from("d4"), Position.from(toPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("룩은 상하좌우 방향으로만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isPawn()).isFalse();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isKnight()).isFalse();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isKing()).isFalse();
    }
}
