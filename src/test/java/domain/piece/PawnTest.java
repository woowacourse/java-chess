package domain.piece;

import domain.board.Board;
import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;
import static domain.board.File.*;
import static domain.piece.PawnMovementDirection.*;
import static domain.board.Rank.*;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @DisplayName("주어진 출발지 -> 도착지를 Pawn이 이동할 수 있는지 검증한다.")
    @MethodSource("checkMovableTestCase")
    @ParameterizedTest
    void checkMovableTest(final PieceColor pieceColor, final PieceColor enemyColor, final Position source, final Position destination) {
        // Given
        Pawn pawn = new Pawn(pieceColor);
        Map<Position, Piece> piecePositions = Map.of(
                position(E, SIX), new Rook(enemyColor),
                position(C, SIX), new Rook(enemyColor),
                position(E, FOUR), new Rook(enemyColor),
                position(C, FOUR), new Rook(enemyColor)
        );
        Board board = new Board(piecePositions);

        // When & Then
        assertThatCode(() -> pawn.move(source, destination, board))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> checkMovableTestCase() {
        return Stream.of(
                Arguments.of(WHITE, BLACK, position(F, TWO), position(F, FOUR), UP_TWO_STEP),
                Arguments.of(WHITE, BLACK, position(C, FOUR), position(C, FIVE), UP_ONE_STEP),
                Arguments.of(WHITE, BLACK, position(D, FIVE), position(E, SIX), UP_RIGHT),
                Arguments.of(WHITE, BLACK, position(D, FIVE), position(C, SIX), UP_LEFT),
                Arguments.of(BLACK, WHITE, position(G, SEVEN), position(G, FIVE), DOWN_TWO_STEP),
                Arguments.of(BLACK, WHITE, position(C, FOUR), position(C, THREE), DOWN_ONE_STEP),
                Arguments.of(BLACK, WHITE, position(D, FIVE), position(E, FOUR), DOWN_RIGHT),
                Arguments.of(BLACK, WHITE, position(D, FIVE), position(C, FOUR), DOWN_LEFT)
        );
    }

    @DisplayName("대각석으로 이동할 경우 목적지에 적 기물이 존재하지 않으면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenCrossStepDestinationNotHasEnemyTestCase")
    @ParameterizedTest
    void throwExceptionWhenCrossStepDestinationNotHasEnemyTest(final PieceColor pieceColor, final Position source, final Position destination) {
        // Given
        Pawn pawn = new Pawn(pieceColor);
        Map<Position, Piece> piecePositions = Collections.emptyMap();
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> pawn.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적 기물이 존재하지 않으면 대각선으로 이동할 수 없습니다");
    }

    private static Stream<Arguments> throwExceptionWhenCrossStepDestinationNotHasEnemyTestCase() {
        return Stream.of(
                Arguments.of(WHITE, position(D, FIVE), position(E, SIX), UP_RIGHT),
                Arguments.of(WHITE, position(D, FIVE), position(C, SIX), UP_LEFT),
                Arguments.of(BLACK, position(D, FIVE), position(E, FOUR), DOWN_RIGHT),
                Arguments.of(BLACK, position(D, FIVE), position(C, FOUR), DOWN_LEFT)
        );
    }

    @DisplayName("두 칸 전진할 경우 출발지가 폰의 시작 위치가 아니면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenOneStepSourceIsNotStartPositionTestCase")
    @ParameterizedTest
    void throwExceptionWhenOneStepSourceIsNotStartPositionTest(final PieceColor pieceColor, final Position source, final Position destination) {
        // Given
        Pawn pawn = new Pawn(pieceColor);
        Map<Position, Piece> piecePositions = Collections.emptyMap();
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> pawn.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 위치가 아니면 두 칸 이동할 수 없습니다.");
    }

    private static Stream<Arguments> throwExceptionWhenOneStepSourceIsNotStartPositionTestCase() {
        return Stream.of(
                Arguments.of(WHITE, position(C, THREE), position(C, FIVE)),
                Arguments.of(BLACK, position(C, FIVE), position(C, THREE))
        );
    }

    @DisplayName("폰이 전진할 시 경로에 기물이 존재하면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenForwardPathHasPieceTestCase")
    @ParameterizedTest
    void throwExceptionWhenForwardPathHasPieceTest(final PieceColor pieceColor, final Position source, final Position destination) {
        // Given
        Pawn pawn = new Pawn(pieceColor);
        Map<Position, Piece> piecePositions = Map.of(
                position(D, THREE), new Rook(WHITE),
                position(C, THREE), new Rook(BLACK)
        );
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> pawn.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("전진시 기물이 존재하는 경로 혹은 목적지로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> throwExceptionWhenForwardPathHasPieceTestCase() {
        return Stream.of(
                Arguments.of(WHITE, position(D, TWO), position(D, FOUR)),
                Arguments.of(BLACK, position(C, FOUR), position(C, THREE))
        );
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
