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

class QueenTest {

    @Test
    @DisplayName("퀸을 생성하는 기능")
    void createQueen() {
        assertThat(new Queen(Color.WHITE)).isInstanceOf(Queen.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("queenMoveValidTestSet")
    @DisplayName("퀸은 상, 하, 좌, 우, 모든 대각선 방향으로 이동거리 제한 없이 움직일 수 있다.")
    void movableValidQueen(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Queen(color).move(from, to, chessBoard));
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("queenMoveValidTestSet")
    @DisplayName("퀸의 이동경로에 기물이 있을 경우 예외가 발생한다.")
    void movableInvalidHurdleQueen(Position from, Position to, Color color) {
        final Map<Position, Piece> board = Fixture.queenMovableHurdleTestSetUp();
        final ChessBoard chessBoard = new ChessBoard(board);

        assertThatThrownBy(() -> new Queen(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 이동입니다.");
    }

    static Stream<Arguments> queenMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d", "1"), Position.of("a", "1"), Color.WHITE),
                Arguments.of(Position.of("d", "1"), Position.of("g", "1"), Color.WHITE),
                Arguments.of(Position.of("d", "1"), Position.of("b", "3"), Color.WHITE),
                Arguments.of(Position.of("d", "1"), Position.of("f", "3"), Color.WHITE),
                Arguments.of(Position.of("d", "8"), Position.of("a", "8"), Color.BLACK),
                Arguments.of(Position.of("d", "8"), Position.of("g", "8"), Color.BLACK),
                Arguments.of(Position.of("d", "8"), Position.of("b", "6"), Color.BLACK),
                Arguments.of(Position.of("d", "8"), Position.of("f", "6"), Color.BLACK)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("queenMoveInvalidTestSet")
    @DisplayName("퀸은 상, 하, 좌, 우, 모든 대각선 방향으로 이동하지 않을 경우 예외가 발생한다.")
    void movableInvalidDirectionQueen(Position from, Position to, Color color) {
        final Map<Position, Piece> board = Map.of();
        final ChessBoard chessBoard = new ChessBoard(board);

        assertThatThrownBy(() -> new Queen(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 있는 경로가 없습니다.");
    }

    static Stream<Arguments> queenMoveInvalidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d", "1"), Position.of("e", "3"), Color.WHITE),
                Arguments.of(Position.of("d", "1"), Position.of("c", "3"), Color.WHITE),
                Arguments.of(Position.of("d", "8"), Position.of("e", "6"), Color.WHITE),
                Arguments.of(Position.of("d", "8"), Position.of("c", "6"), Color.WHITE)
        );
    }
}
