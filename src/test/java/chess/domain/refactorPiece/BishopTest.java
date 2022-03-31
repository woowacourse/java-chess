package chess.domain.refactorPiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Fixture;
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

class BishopTest {

    @Test
    @DisplayName("비숍을 생성할 수 있다.")
    void createBishop() {
        assertThat(new Bishop(Color.WHITE)).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("bishopMoveValidTestSet")
    @DisplayName("비숍은 대각선 방향으로 이동거리 제한 없이 움직일 수 있다.")
    void movableValidBishop(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Bishop(color).move(from, to, chessBoard));
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("bishopMoveValidTestSet")
    @DisplayName("비숍의 이동경로에 기물이 있을 경우 예외가 발생한다.")
    void movableInvalidHurdleBishop(Position from, Position to, Color color) {
        final Map<Position, Piece> board = Fixture.bishopMovableHurdleTestSetUp();
        final ChessBoard chessBoard = new ChessBoard(board);

        assertThatThrownBy(() -> new Bishop(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 이동입니다.");
    }

    static Stream<Arguments> bishopMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("c", "1"), Position.of("e", "3"), Color.WHITE),
                Arguments.of(Position.of("h", "1"), Position.of("f", "3"), Color.WHITE),
                Arguments.of(Position.of("c", "8"), Position.of("e", "6"), Color.BLACK),
                Arguments.of(Position.of("h", "8"), Position.of("f", "6"), Color.BLACK)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("bishopMoveInvalidTestSet")
    @DisplayName("비숍은 대각선 방향으로 이동하지 않을 경우 예외가 발생한다.")
    void movableInvalidBishop(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertThatThrownBy(() -> new Bishop(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 있는 경로가 없습니다.");
    }

    static Stream<Arguments> bishopMoveInvalidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("c", "1"), Position.of("e", "4"), Color.WHITE),
                Arguments.of(Position.of("h", "1"), Position.of("g", "1"), Color.WHITE),
                Arguments.of(Position.of("c", "8"), Position.of("e", "7"), Color.BLACK),
                Arguments.of(Position.of("h", "8"), Position.of("g", "6"), Color.BLACK)
        );
    }
}
