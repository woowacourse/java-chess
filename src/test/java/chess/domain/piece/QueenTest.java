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

public class QueenTest {

    private static final Board emptyBoard = new Board(HashMap::new);

    @DisplayName("퀸은 상하좌우 대각선 방향으로 이동 가능")
    @ParameterizedTest
    @ValueSource(strings = {"e7", "g5", "e3", "c5", "c7", "g7", "g3", "c3"})
    void move(String to) {
        Piece queen = new Queen(Color.WHITE);

        assertThatCode(() -> queen.checkMovingRange(emptyBoard, Position.from("e5"), Position.from(to)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸의 도착지가 상하좌우 대각선 방향이 아닌 경우 예외 발생")
    void invalidMove() {
        Piece queen = new Queen(Color.WHITE);

        assertThatThrownBy(() -> queen.checkMovingRange(emptyBoard, Position.from("e5"), Position.from("f7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("폰인지 확인")
    void isPawn() {
        Piece queen = new Queen(Color.WHITE);

        assertThat(queen.isPawn()).isFalse();
    }

    @Test
    @DisplayName("나이트인지 확인")
    void isKnight() {
        Piece queen = new Queen(Color.WHITE);

        assertThat(queen.isKnight()).isFalse();
    }

    @Test
    @DisplayName("킹인지 확인")
    void isKing() {
        Piece queen = new Queen(Color.WHITE);

        assertThat(queen.isKing()).isFalse();
    }
}
