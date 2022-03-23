package chess;

import static chess.Col.*;
import static chess.piece.Piece.*;
import static chess.Row.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.*;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
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
            knight(Color.BLACK, new Position(B, EIGHT)),
            new Bishop(Color.BLACK, new Position(C, EIGHT)),
            new Queen(Color.BLACK, new Position(D, EIGHT)),
            king(Color.BLACK, new Position(E, EIGHT)),
            new Bishop(Color.BLACK, new Position(F, EIGHT)),
            knight(Color.BLACK, new Position(G, EIGHT)),
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
            knight(Color.WHITE, new Position(B, ONE)),
            new Bishop(Color.WHITE, new Position(C, ONE)),
            new Queen(Color.WHITE, new Position(D, ONE)),
            king(Color.WHITE, new Position(E, ONE)),
            new Bishop(Color.WHITE, new Position(F, ONE)),
            knight(Color.WHITE, new Position(G, ONE)),
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
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))));

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, SEVEN)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideFirstMoveForwardPawn")
    @DisplayName("폰을 처음에 앞으로 한칸 또는 두칸을 움직일 수 있다.")
    void movePawn(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(color, from)));

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
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Pawn(Color.BLACK, new Position(A, SEVEN)));
        });
    }

    @Test
    @DisplayName("폰이 처음 움직인 이후 부터는 두칸 이상 이동시 예외 발생")
    void throwExceptionMovePawnOverOneSpaceAfterFirstMove() {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))));

        chessBoard.move(new Position(A, SEVEN), new Position(A, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SIX), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(new Pawn(Color.BLACK, new Position(A, SIX)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideMoveBackwardPawn")
    @DisplayName("폰은 뒤로 움직일 경우 예외가 발생한다.")
    void throwExceptionMovePawnBackward(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(color, from)));

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
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(Color.WHITE, from)));

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
        ChessBoard chessBoard = new ChessBoard(List.of(new Rook(Color.BLACK, from)));

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
        ChessBoard chessBoard = new ChessBoard(List.of(new Rook(Color.BLACK, from)));

        assertAll(()->{
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
        ChessBoard chessBoard = new ChessBoard(List.of(new Bishop(Color.BLACK, from)));

        assertAll(()->{
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
        ChessBoard chessBoard = new ChessBoard(List.of(new Bishop(Color.BLACK, from)));

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
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.BLACK, from)));

        assertAll(()->{
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
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.BLACK, from)));

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
}
