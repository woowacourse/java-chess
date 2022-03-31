package chess.domain.refactorPiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorPosition.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @Test
    @DisplayName("나이트를 생성할 수 있다.")
    void createKnight() {
        assertThat(new Knight(Color.WHITE)).isInstanceOf(Knight.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("knightMoveValidTestSet")
    @DisplayName("나이트는 지정된 방향으로만 1칸 움직일 수 있다.")
    void movableValidKnight(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Knight(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> knightMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("b", "1"), Position.of("c", "3"), Color.WHITE),
                Arguments.of(Position.of("b", "1"), Position.of("a", "3"), Color.WHITE),
                Arguments.of(Position.of("c", "3"), Position.of("b", "1"), Color.WHITE),
                Arguments.of(Position.of("a", "3"), Position.of("b", "1"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("a", "1"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("e", "1"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("a", "3"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("e", "3"), Color.WHITE)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("knightMoveInvalidTestSet")
    @DisplayName("나이트는 지정된 방향으로 움직이지 않을 경우 예외가 발생한다.")
    void movableInvalidKnight(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertThatThrownBy(() -> new Knight(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 이동입니다.");
    }

    static Stream<Arguments> knightMoveInvalidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("b", "1"), Position.of("c", "4"), Color.WHITE),
                Arguments.of(Position.of("b", "1"), Position.of("a", "4"), Color.WHITE)
        );
    }
}
