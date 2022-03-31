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

public class KingTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("킹이 이동할 수 있는 범위로 이동")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6", "f5", "f4", "e4", "d4", "d5"})
    void checkMovingRange(String to) {
        Piece king = new King(Color.WHITE);

        assertThatCode(() -> king.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("킹이 이동할 수 없는 범위로 이동 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"h5", "a5", "e8", "e3", "h8", "a1"})
    void checkMovingRangeException(String to) {
        assertThatThrownBy(() -> new King(Color.WHITE).checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isPawn()).isFalse();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isKnight()).isFalse();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece king = new King(Color.WHITE);

        assertThat(king.isKing()).isTrue();
    }
}
