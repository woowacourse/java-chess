package chess;

import static chess.Col.*;
import static chess.Row.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.*;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    @Test
    @DisplayName("체스판 정상적인 초기화")
    void createChessBoard() {
        ChessBoard chessBoard = new ChessBoard();

        List<Piece> pieces = chessBoard.getPieces();

        assertAll(() -> {
            assertThat(pieces).hasSize(32);
            assertPieces(pieces);
        });
    }

    private static void assertPieces(List<Piece> pieces) {
        assertThat(pieces).contains(
            new Rook(Color.BLACK, new Position(A, EIGHT)),
            new Knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            new King(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            new Knight(Color.BLACK, new Position(G, EIGHT)),
            new Rook(Color.BLACK, new Position(H, EIGHT)),
            new Pawn(Color.BLACK, new Position(A, SEVEN)),
            new Pawn(Color.BLACK, new Position(B, SEVEN)),
            new Pawn(Color.BLACK, new Position(C, SEVEN)),
            new Pawn(Color.BLACK, new Position(D, SEVEN)),
            new Pawn(Color.BLACK, new Position(E, SEVEN)),
            new Pawn(Color.BLACK, new Position(F, SEVEN)),
            new Pawn(Color.BLACK, new Position(G, SEVEN)),
            new Pawn(Color.BLACK, new Position(H, SEVEN)),
            new Rook(Color.WHITE, new Position(A, ONE)),
            new Knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            new King(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            new Knight(Color.WHITE, new Position(G, ONE)),
            new Rook(Color.WHITE, new Position(H, ONE)),
            new Pawn(Color.WHITE, new Position(A, TWO)),
            new Pawn(Color.WHITE, new Position(B, TWO)),
            new Pawn(Color.WHITE, new Position(C, TWO)),
            new Pawn(Color.WHITE, new Position(D, TWO)),
            new Pawn(Color.WHITE, new Position(E, TWO)),
            new Pawn(Color.WHITE, new Position(F, TWO)),
            new Pawn(Color.WHITE, new Position(G, TWO)),
            new Pawn(Color.WHITE, new Position(H, TWO)));
    }

    @Test
    @DisplayName("움직이려는 위치에 기물이 존재하지 않으면 예외 발생")
    void selectedNotFoundPieces() {
        ChessBoard chessBoard = new ChessBoard();

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FIVE), new Position(F, SIX)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("from과 to 위치가 동일한 경우 예외발생")
    void selectSameFromAndToPosition() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, SEVEN)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideFirstMoveForwardPawn")
    @DisplayName("폰을 처음에 앞으로 한칸 또는 두칸을 움직일 수 있다.")
    void movePawn(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(color, from)), color);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new Pawn(color, to));
    }

    private static Stream<Arguments> provideFirstMoveForwardPawn() {
        return Stream.of(
            Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, SIX)),
            Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, THREE)),
            Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, FIVE)),
            Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, FOUR))
        );
    }

    @Test
    @DisplayName("폰은 처음에는 3칸 이상 이동 시 예외가 발생한다.")
    void throwExceptionMovePawnOverTwoSpaceWhenFirstMove() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces())
                .contains(new Pawn(Color.BLACK, new Position(A, SEVEN)));
        });
    }

    @Test
    @DisplayName("폰이 처음 움직인 이후 부터는 두칸 이상 이동시 예외 발생")
    void throwExceptionMovePawnOverOneSpaceAfterFirstMove() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        chessBoard.move(new Position(A, SEVEN), new Position(A, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SIX), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces())
                .contains(new Pawn(Color.BLACK, new Position(A, SIX)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideMoveBackwardPawn")
    @DisplayName("폰은 뒤로 움직일 경우 예외가 발생한다.")
    void throwExceptionMovePawnBackward(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(color, from)), color);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Pawn(color, from));
        });
    }

    private static Stream<Arguments> provideMoveBackwardPawn() {
        return Stream.of(
            Arguments.of(Color.BLACK, new Position(A, SEVEN), new Position(A, EIGHT)),
            Arguments.of(Color.WHITE, new Position(A, TWO), new Position(A, ONE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveSidePawn")
    @DisplayName("폰이 양옆으로 움직이려고 할 경우 예외 발생")
    void throwExceptionPawnMoveSide(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.WHITE, from)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Pawn(Color.WHITE, from));
        });
    }

    private static Stream<Arguments> provideMoveSidePawn() {
        return Stream.of(
            Arguments.of(new Position(A, TWO), new Position(B, THREE)),
            Arguments.of(new Position(A, TWO), new Position(B, TWO)),
            Arguments.of(new Position(B, TWO), new Position(A, TWO))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMoveCollinearRook")
    @DisplayName("룩은 동일선상으로 제한 없이 이동")
    void moveRookCollinearPositionUnlimitedDistance(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Rook(Color.BLACK, from)), Color.BLACK);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new Rook(Color.BLACK, to));
    }

    private static Stream<Arguments> provideMoveCollinearRook() {
        return Stream.of(
            Arguments.of(new Position(A, EIGHT), new Position(A, FIVE)),
            Arguments.of(new Position(H, EIGHT), new Position(C, EIGHT)),
            Arguments.of(new Position(H, ONE), new Position(H, THREE)),
            Arguments.of(new Position(A, ONE), new Position(A, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveRook")
    @DisplayName("룩이 전후양옆외의 방향으로 이동 시 예외 발생")
    void throwExceptionWhenRookMoveInvalidPosition(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Rook(Color.BLACK, from)), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Rook(Color.BLACK, from));
        });
    }

    private static Stream<Arguments> provideInvalidMoveRook() {
        return Stream.of(
            Arguments.of(new Position(A, EIGHT), new Position(B, FIVE)),
            Arguments.of(new Position(H, EIGHT), new Position(C, SEVEN)),
            Arguments.of(new Position(H, ONE), new Position(B, THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveBishop")
    @DisplayName("비숍은 대각선외에는 움직일 수 없다.")
    void throwExceptionInvalidMoveBishop(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Bishop(Color.BLACK, from)), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Bishop(Color.BLACK, from));
        });
    }

    private static Stream<Arguments> provideInvalidMoveBishop() {
        return Stream.of(
            Arguments.of(new Position(C, EIGHT), new Position(D, THREE)),
            Arguments.of(new Position(C, EIGHT), new Position(G, FIVE)),
            Arguments.of(new Position(C, ONE), new Position(B, FIVE)),
            Arguments.of(new Position(C, ONE), new Position(F, SIX))
        );
    }

    @ParameterizedTest
    @MethodSource("provideCrossMoveBishop")
    @DisplayName("비숍은 대각선으로 이동할 수 있다.")
    void moveCrossBishop(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Bishop(Color.BLACK, from)), Color.BLACK);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new Bishop(Color.BLACK, to));
    }

    private static Stream<Arguments> provideCrossMoveBishop() {
        return Stream.of(
            Arguments.of(new Position(C, EIGHT), new Position(F, FIVE)),
            Arguments.of(new Position(C, EIGHT), new Position(A, SIX)),
            Arguments.of(new Position(C, ONE), new Position(B, TWO)),
            Arguments.of(new Position(C, ONE), new Position(E, THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveQueen")
    @DisplayName("퀸은 동일선상외에는 이동 시 예외 발생")
    void moveInvalidMoveQueen(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.BLACK, from)), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Queen(Color.BLACK, from));
        });
    }

    private static Stream<Arguments> provideInvalidMoveQueen() {
        return Stream.of(
            Arguments.of(new Position(D, EIGHT), new Position(F, FOUR)),
            Arguments.of(new Position(D, EIGHT), new Position(A, SIX)),
            Arguments.of(new Position(D, ONE), new Position(E, FIVE)),
            Arguments.of(new Position(D, ONE), new Position(B, TWO))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveQueen")
    @DisplayName("퀸은 동일선상으로 이동")
    void moveCrossOrSameRowOrColMoveQueen(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.BLACK, from)), Color.BLACK);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new Queen(Color.BLACK, to));
    }

    private static Stream<Arguments> provideValidMoveQueen() {
        return Stream.of(
            Arguments.of(new Position(D, EIGHT), new Position(D, FOUR)),
            Arguments.of(new Position(D, EIGHT), new Position(H, EIGHT)),
            Arguments.of(new Position(D, EIGHT), new Position(A, EIGHT)),
            Arguments.of(new Position(D, EIGHT), new Position(F, SIX)),
            Arguments.of(new Position(D, EIGHT), new Position(A, FIVE)),
            Arguments.of(new Position(D, ONE), new Position(D, FOUR)),
            Arguments.of(new Position(D, ONE), new Position(F, THREE)),
            Arguments.of(new Position(D, ONE), new Position(A, FOUR))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveKing")
    @DisplayName("킹이 2칸 이상 이동 시 예외 발생")
    void throwExceptionKingMoveOverOneSquare(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new King(Color.BLACK, from)), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new King(Color.BLACK, from));
        });
    }

    private static Stream<Arguments> provideInvalidMoveKing() {
        return Stream.of(
            Arguments.of(new Position(E, FIVE), new Position(E, THREE)),
            Arguments.of(new Position(E, FIVE), new Position(G, THREE)),
            Arguments.of(new Position(E, FIVE), new Position(C, THREE)),
            Arguments.of(new Position(E, FIVE), new Position(D, THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveKing")
    @DisplayName("킹은 인접한 칸으로만 이동할 수 있다.")
    void moveKingOneSquareToAdjacent(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new King(Color.BLACK, from)), Color.BLACK);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new King(Color.BLACK, to));
    }

    private static Stream<Arguments> provideValidMoveKing() {
        return Stream.of(
            Arguments.of(new Position(E, FIVE), new Position(E, FOUR)),
            Arguments.of(new Position(E, FIVE), new Position(F, SIX)),
            Arguments.of(new Position(E, FIVE), new Position(F, FIVE)),
            Arguments.of(new Position(E, FIVE), new Position(F, FOUR)),
            Arguments.of(new Position(E, FIVE), new Position(D, FOUR)),
            Arguments.of(new Position(E, FIVE), new Position(D, FIVE)),
            Arguments.of(new Position(E, FIVE), new Position(D, SIX)),
            Arguments.of(new Position(E, FIVE), new Position(E, SIX))
        );
    }

    @Test
    @DisplayName("나이트가 이동할 수 없는 위치로 이동 시 예외 발생")
    void moveKnightToInvalidPosition() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Knight(Color.BLACK, new Position(G, EIGHT))), Color.BLACK);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(G, EIGHT), new Position(F, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces())
                .contains(new Knight(Color.BLACK, new Position(G, EIGHT)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideValidMoveKnight")
    @DisplayName("나이트는 직선으로 1칸 이동 후 대각선으로 1칸 움직인다.")
    void moveKnightToValidPosition(Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Knight(Color.BLACK, from)), Color.BLACK);

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(new Knight(Color.BLACK, to));
    }

    private static Stream<Arguments> provideValidMoveKnight() {
        return Stream.of(
            Arguments.of(new Position(E, FIVE), new Position(G, FOUR)),
            Arguments.of(new Position(E, FIVE), new Position(F, THREE)),
            Arguments.of(new Position(E, FIVE), new Position(D, THREE)),
            Arguments.of(new Position(E, FIVE), new Position(C, FOUR)),
            Arguments.of(new Position(E, FIVE), new Position(C, SIX)),
            Arguments.of(new Position(E, FIVE), new Position(D, SEVEN)),
            Arguments.of(new Position(E, FIVE), new Position(F, SEVEN)),
            Arguments.of(new Position(E, FIVE), new Position(G, SIX))
        );
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(List.of(new King(Color.WHITE, new Position(E, FIVE)),
            new King(Color.BLACK, new Position(B, FIVE))), Color.WHITE);

        chessBoard.move(new Position(E, FIVE), new Position(E, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, SIX), new Position(E, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                new King(Color.WHITE, new Position(E, SIX)),
                new King(Color.BLACK, new Position(B, FIVE)));
        });

    }

    @Test
    @DisplayName("플레이어가 번갈아가며 기물을 움직인다.")
    void moveEachColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(List.of(new King(Color.WHITE, new Position(E, FIVE)),
            new King(Color.BLACK, new Position(B, FIVE))), Color.WHITE);

        assertThatCode(() -> {
            chessBoard.move(new Position(E, FIVE), new Position(E, SIX));
            chessBoard.move(new Position(B, FIVE), new Position(B, SIX));
            chessBoard.move(new Position(E, SIX), new Position(E, FIVE));
            chessBoard.move(new Position(B, SIX), new Position(B, FIVE));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 색깔의 기물이 있는 위치로 이동 시 예외 발생")
    void throwExceptionWhenMovePieceToSameColorPiecePosition() {
        ChessBoard chessBoard = new ChessBoard(List.of(new King(Color.WHITE, new Position(E, FIVE)),
            new Pawn(Color.WHITE, new Position(E, SIX))), Color.WHITE);
        
        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, FIVE), new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                new King(Color.WHITE, new Position(E, FIVE)),
                new Pawn(Color.WHITE, new Position(E, SIX)));
        });

    }
}
