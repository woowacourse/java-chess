package chess.domain.refactorPiece;

import static org.assertj.core.api.Assertions.assertThat;
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

class PawnTest {

    @Test
    @DisplayName("폰을 생성하는 기능")
    void createPawn() {
        assertAll(
                () -> assertThat(new Pawn(Color.WHITE)).isInstanceOf(Pawn.class),
                () -> assertThat(new Pawn(Color.BLACK)).isInstanceOf(Pawn.class)
        );
    }

    @ParameterizedTest
    @MethodSource("whiteTeamWithPawnSingleMoveValidTestSet")
    @DisplayName("폰이 흰색 진영일 경우 상 방향으로 1칸 움직일 수 있다.")
    void whiteTeamWithPawnSingleMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> whiteTeamWithPawnSingleMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "2"), Position.of("a","3"), Color.WHITE)
        );
    }

    @ParameterizedTest
    @MethodSource("whiteTeamWithPawnMultiMoveValidTestSet")
    @DisplayName("폰이 흰색 진영이고 최초 위치일 경우 상 방향으로 1칸 또는 2칸 움직일 수 있다.")
    void whiteTeamWithPawnMultiMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> whiteTeamWithPawnMultiMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "2"), Position.of("a","3"), Color.WHITE),
                Arguments.of(Position.of("a", "2"), Position.of("a","4"), Color.WHITE)
        );
    }

    @ParameterizedTest
    @MethodSource("whiteTeamWithPawnDiagonalMoveValidTestSet")
    @DisplayName("폰이 흰색 진영이고 대각선 상 방향에 상대방 기물이 있는 경우 대각선 상 방향으로 움직일 수 있다.")
    void whiteTeamWithPawnDiagonalMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Fixture.whitePawnDiagonalTestSetUp());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> whiteTeamWithPawnDiagonalMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("c", "2"), Position.of("b","3"), Color.WHITE),
                Arguments.of(Position.of("c", "2"), Position.of("d","3"), Color.WHITE)
        );
    }

    @ParameterizedTest
    @MethodSource("blackTeamWithPawnSingleMoveValidTestSet")
    @DisplayName("폰이 검은색 진영일 경우 하 방향으로 1칸 움직일 수 있다.")
    void blackTeamWithPawnSingleMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> blackTeamWithPawnSingleMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "7"), Position.of("a","6"), Color.BLACK)
        );
    }

    @ParameterizedTest
    @MethodSource("blackTeamWithPawnMultiMoveValidTestSet")
    @DisplayName("폰이 검은색 진영이고 최초 위치일 경우 하 방향으로 1칸 또는 2칸 움직일 수 있다.")
    void blackTeamWithPawnMultiMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Map.of());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> blackTeamWithPawnMultiMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a", "7"), Position.of("a","6"), Color.BLACK),
                Arguments.of(Position.of("a", "7"), Position.of("a","5"), Color.BLACK)
        );
    }

    @ParameterizedTest
    @MethodSource("blackTeamWithPawnDiagonalMoveValidTestSet")
    @DisplayName("폰이 검은색 진영이고 대각선 하 방향에 상대방 기물이 있는 경우 대각선 하 방향으로 움직일 수 있다.")
    void blackTeamWithPawnDiagonalMove(Position from, Position to, Color color) {
        final ChessBoard chessBoard = new ChessBoard(Fixture.blackPawnDiagonalTestSetUp());

        assertDoesNotThrow(() -> new Pawn(color).move(from, to, chessBoard));
    }

    static Stream<Arguments> blackTeamWithPawnDiagonalMoveValidTestSet() {
        return Stream.of(
                Arguments.of(Position.of("c", "7"), Position.of("b","6"), Color.BLACK),
                Arguments.of(Position.of("c", "7"), Position.of("d","6"), Color.BLACK)
        );
    }
}
