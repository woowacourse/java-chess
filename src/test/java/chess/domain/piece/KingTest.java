package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @Test
    @DisplayName("킹을 생성할 수 있다.")
    void createKing() {
        Assertions.assertThat(new King(Color.WHITE)).isInstanceOf(King.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("kingMoveValidTestSet")
    @DisplayName("킹은 상, 하, 좌, 우 그리고 대각선 방향으로 1칸 움직일 수 있다.")
    void movableValidKing(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new King(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> kingMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("e", "2"), Position.of("e", "3"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("c", "1"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("f", "1"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("d", "1"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("d", "2"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("f", "2"), Color.WHITE),
                Arguments.of(Position.of("e", "2"), Position.of("d", "1"), Color.WHITE),
                Arguments.of(Position.of("e", "2"), Position.of("f", "1"), Color.WHITE)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("kingMoveInvalidTestSet")
    @DisplayName("킹은 상, 하, 좌, 우 그리고 대각선 방향으로 1칸 이동하지 않을 경우 예외가 발생한다.")
    void movableInvalidKing(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertThatThrownBy(() -> new King(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 이동입니다.");
    }

    static Stream<Arguments> kingMoveInvalidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("e", "1"), Position.of("e","3"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("c","1"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("g","1"), Color.WHITE),
                Arguments.of(Position.of("e", "3"), Position.of("e","1"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("f","3"), Color.WHITE),
                Arguments.of(Position.of("e", "1"), Position.of("c","3"), Color.WHITE),
                Arguments.of(Position.of("e", "3"), Position.of("c","1"), Color.WHITE),
                Arguments.of(Position.of("e", "3"), Position.of("g","1"), Color.WHITE)
        );
    }
}
