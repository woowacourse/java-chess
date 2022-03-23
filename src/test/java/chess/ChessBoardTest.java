package chess;

import static chess.Col.*;
import static chess.Piece.*;
import static chess.Row.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
            rook(Color.BLACK, new Position(A, EIGHT)),
            knight(Color.BLACK, new Position(B, EIGHT)),
            bishop(Color.BLACK, new Position(C, EIGHT)),
            queen(Color.BLACK, new Position(D, EIGHT)),
            king(Color.BLACK, new Position(E, EIGHT)),
            bishop(Color.BLACK, new Position(F, EIGHT)),
            knight(Color.BLACK, new Position(G, EIGHT)),
            rook(Color.BLACK, new Position(H, EIGHT)),
            pawn(Color.BLACK, new Position(A, SEVEN)),
            pawn(Color.BLACK, new Position(B, SEVEN)),
            pawn(Color.BLACK, new Position(C, SEVEN)),
            pawn(Color.BLACK, new Position(D, SEVEN)),
            pawn(Color.BLACK, new Position(E, SEVEN)),
            pawn(Color.BLACK, new Position(F, SEVEN)),
            pawn(Color.BLACK, new Position(G, SEVEN)),
            pawn(Color.BLACK, new Position(H, SEVEN)),
            rook(Color.WHITE, new Position(A, ONE)),
            knight(Color.WHITE, new Position(B, ONE)),
            bishop(Color.WHITE, new Position(C, ONE)),
            queen(Color.WHITE, new Position(D, ONE)),
            king(Color.WHITE, new Position(E, ONE)),
            bishop(Color.WHITE, new Position(F, ONE)),
            knight(Color.WHITE, new Position(G, ONE)),
            rook(Color.WHITE, new Position(H, ONE)),
            pawn(Color.WHITE, new Position(A, TWO)),
            pawn(Color.WHITE, new Position(B, TWO)),
            pawn(Color.WHITE, new Position(C, TWO)),
            pawn(Color.WHITE, new Position(D, TWO)),
            pawn(Color.WHITE, new Position(E, TWO)),
            pawn(Color.WHITE, new Position(F, TWO)),
            pawn(Color.WHITE, new Position(G, TWO)),
            pawn(Color.WHITE, new Position(H, TWO)));
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
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(Color.BLACK, new Position(A, SEVEN))));

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, SEVEN)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideFirstMoveForwardPawn")
    @DisplayName("폰을 처음에 앞으로 한칸 또는 두칸을 움직일 수 있다.")
    void movePawn(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(color, from)));

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(pawn(color, to));
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
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(Color.BLACK, new Position(A, SEVEN))));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(pawn(Color.BLACK, new Position(A, SEVEN)));
        });
    }

    @Test
    @DisplayName("폰이 처음 움직인 이후 부터는 두칸 이상 이동시 예외 발생")
    void throwExceptionMovePawnOverOneSpaceAfterFirstMove() {
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(Color.BLACK, new Position(A, SEVEN))));

        chessBoard.move(new Position(A, SEVEN), new Position(A, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(A, SIX), new Position(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(pawn(Color.BLACK, new Position(A, SIX)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideMoveBackwardPawn")
    @DisplayName("폰은 뒤로 움직일 경우 예외가 발생한다.")
    void throwExceptionMovePawnBackward(Color color, Position from, Position to) {
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(color, from)));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(pawn(color, from));
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
        ChessBoard chessBoard = new ChessBoard(List.of(pawn(Color.WHITE, from)));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(pawn(Color.WHITE, from));
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
        ChessBoard chessBoard = new ChessBoard(List.of(rook(Color.BLACK, from)));

        chessBoard.move(from, to);

        assertThat(chessBoard.getPieces()).contains(rook(Color.BLACK, to));
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
        ChessBoard chessBoard = new ChessBoard(List.of(rook(Color.BLACK, from)));

        assertAll(()->{
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).contains(rook(Color.BLACK, from));
        });
    }

    private static Stream<Arguments> provideInvalidMoveRook() {
        return Stream.of(
            Arguments.of(new Position(A, EIGHT), new Position(B, FIVE)),
            Arguments.of(new Position(H, EIGHT), new Position(C, SEVEN)),
            Arguments.of(new Position(H, ONE), new Position(B, THREE))
        );
    }
}
