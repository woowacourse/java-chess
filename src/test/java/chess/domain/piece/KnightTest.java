package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @DisplayName("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "d7", "f7", "g6", "g4", "f3", "d3", "c4"})
    void move(String to) {
        Piece knight = new Knight(Color.WHITE);

        assertThatCode(() -> knight.checkPieceMoveRange(new Board(), Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @DisplayName("나이트 이동 범위를 벗어날 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"e6", "h5"})
    void invalidMove(String to) {
        Piece knight = new Knight(Color.WHITE);

        assertThatThrownBy(() -> knight.checkPieceMoveRange(new Board(), Position.from("e5"), Position.from(to)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동할 수 있습니다.");
    }
}
