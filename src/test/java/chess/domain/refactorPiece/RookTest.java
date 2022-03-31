package chess.domain.refactorPiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

public class RookTest {

    @Test
    @DisplayName("룩을 생성할 수 있다.")
    void createRook() {
        assertThat(new Rook(Color.WHITE)).isInstanceOf(Rook.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("rookMoveValidTestSet")
    @DisplayName("룩은 상, 하, 좌, 우 방향으로 이동거리 제한 없이 움직일 수 있다.")
    void movableValidRook(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Rook(color).move(from, to, chessBoard));
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("rookMoveValidTestSet")
    @DisplayName("룩의 이동경로에 기물이 있을 경우 예외가 발생한다.")
    void movableInvalidHurdleRook(Position from, Position to, Color color) {
        final Map<Position, Piece> board = Fixture.rookMovableHurdleTestSetUp();
        final ChessBoard chessBoard = new ChessBoard(board);

        assertThatThrownBy(() -> new Rook(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 이동입니다.");
    }

    static Stream<Arguments> rookMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "1"), Position.of("h", "1"), Color.WHITE),
                Arguments.of(Position.of("h", "1"), Position.of("a", "1"), Color.WHITE),
                Arguments.of(Position.of("a", "8"), Position.of("a", "1"), Color.BLACK),
                Arguments.of(Position.of("h", "8"), Position.of("h", "1"), Color.BLACK)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("rookMoveInvalidTestSet")
    @DisplayName("룩은 상, 하, 좌, 우 방향으로 이동하지 않을 경우 예외가 발생한다.")
    void movableInvalidDirectionRook(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertThatThrownBy(() -> new Rook(color).move(from, to, chessBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 있는 경로가 없습니다.");
    }

    static Stream<Arguments> rookMoveInvalidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "1"), Position.of("b", "2"), Color.WHITE),
                Arguments.of(Position.of("h", "1"), Position.of("e", "2"), Color.WHITE),
                Arguments.of(Position.of("a", "8"), Position.of("b", "7"), Color.BLACK),
                Arguments.of(Position.of("h", "8"), Position.of("e", "7"), Color.BLACK)
        );
    }
}
