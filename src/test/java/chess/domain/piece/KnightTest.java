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

class KnightTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("나이트가 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "d7", "f7", "g6", "g4", "f3", "d3", "c4"})
    void checkMovingRange(String to) {
        Piece knight = new Knight(Color.WHITE);

        assertThatCode(() -> knight.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("나이트가 이동할 수 없는 범위로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"e6", "h5"})
    void checkMovingRangeException(String to) {
        Piece knight = new Knight(Color.WHITE);

        assertThatThrownBy(() -> knight.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece knight = new Knight(Color.WHITE);

        assertThat(knight.isPawn()).isFalse();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece knight = new Knight(Color.WHITE);

        assertThat(knight.isKnight()).isTrue();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece knight = new Knight(Color.WHITE);

        assertThat(knight.isKing()).isFalse();
    }
}
