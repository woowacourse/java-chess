package chess;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.*;
import chess.position.Position;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessGameTest {

    @Test
    @DisplayName("체스판 정상적인 초기화")
    void createChessBoard() {
        ChessGame chessGame = new ChessGame();

        Collection<Square> squares = chessGame.getSquare();

        assertAll(() -> {
            assertThat(squares).hasSize(32);
            assertPieces(squares);
        });
    }

    private static void assertPieces(Collection<Square> squares) {
        assertThat(squares).contains(
            new Square(new Rook(Color.BLACK), new Position(A, EIGHT)),
            new Square(new Knight(Color.BLACK), new Position(B, EIGHT)),
            new Square(new Bishop(Color.BLACK), new Position(C, EIGHT)),
            new Square(new Queen(Color.BLACK), new Position(D, EIGHT)),
            new Square(new King(Color.BLACK), new Position(E, EIGHT)),
            new Square(new Bishop(Color.BLACK), new Position(F, EIGHT)),
            new Square(new Knight(Color.BLACK), new Position(G, EIGHT)),
            new Square(new Rook(Color.BLACK), new Position(H, EIGHT)),
            new Square(new Pawn(Color.BLACK), new Position(A, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(B, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(C, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(D, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(E, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(F, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(G, SEVEN)),
            new Square(new Pawn(Color.BLACK), new Position(H, SEVEN)),
            new Square(new Rook(Color.WHITE), new Position(A, ONE)),
            new Square(new Knight(Color.WHITE), new Position(B, ONE)),
            new Square(new Bishop(Color.WHITE), new Position(C, ONE)),
            new Square(new Queen(Color.WHITE), new Position(D, ONE)),
            new Square(new King(Color.WHITE), new Position(E, ONE)),
            new Square(new Bishop(Color.WHITE), new Position(F, ONE)),
            new Square(new Knight(Color.WHITE), new Position(G, ONE)),
            new Square(new Rook(Color.WHITE), new Position(H, ONE)),
            new Square(new Pawn(Color.WHITE), new Position(A, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(B, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(C, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(D, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(E, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(F, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(G, TWO)),
            new Square(new Pawn(Color.WHITE), new Position(H, TWO)));
    }

    @Test
    @DisplayName("움직이려는 위치에 기물이 존재하지 않으면 예외 발생")
    void selectedNotFoundPieces() {
        ChessGame chessGame = new ChessGame();

        assertThatThrownBy(() -> chessGame.move(new Position(D, FIVE), new Position(F, SIX)))
            .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("from과 to 위치가 동일한 경우 예외발생")
    void selectSameFromAndToPosition() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new Pawn(Color.BLACK), new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessGame.move(new Position(A, SEVEN), new Position(A, SEVEN)))
            .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 위치로 기물을 이동시킬 경우 예외 발생")
    void movePieceToUnmovablePosition() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new Pawn(Color.BLACK), new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessGame.move(new Position(A, SEVEN), new Position(B, SEVEN)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new King(Color.WHITE), new Position(E, FIVE)),
                new Square(new King(Color.BLACK), new Position(B, FIVE))), Color.WHITE);

        chessGame.move(new Position(E, FIVE), new Position(E, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessGame.move(new Position(E, SIX), new Position(E, FIVE)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
                new Square(new King(Color.WHITE), new Position(E, SIX)),
                new Square(new King(Color.BLACK), new Position(B, FIVE)));
        });

    }

    @Test
    @DisplayName("플레이어가 번갈아가며 기물을 움직인다.")
    void moveEachColorPieceSubsequently() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new King(Color.WHITE), new Position(E, FIVE)),
                new Square(new King(Color.BLACK), new Position(B, FIVE))), Color.WHITE);

        assertThatCode(() -> {
            chessGame.move(new Position(E, FIVE), new Position(E, SIX));
            chessGame.move(new Position(B, FIVE), new Position(B, SIX));
            chessGame.move(new Position(E, SIX), new Position(E, FIVE));
            chessGame.move(new Position(B, SIX), new Position(B, FIVE));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 색깔의 기물이 있는 위치로 이동 시 예외 발생")
    void throwExceptionWhenMovePieceToSameColorPiecePosition() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new King(Color.WHITE), new Position(E, FIVE)),
                new Square(new Pawn(Color.WHITE), new Position(E, SIX))), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessGame.move(new Position(E, FIVE), new Position(E, SIX)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
                new Square(new King(Color.WHITE), new Position(E, FIVE)),
                new Square(new Pawn(Color.WHITE), new Position(E, SIX)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideHasObstacleVerticalAndHorizontalWay")
    @DisplayName("움직일 때 장애물이 있을 경우 예외 발생")
    void throwExceptionWhenHasObstacleMoveToDestination(Position from, Position to,
        Position obstacle) {
        ChessGame chessGame = new ChessGame(Set.of(new Square(new Queen(Color.WHITE), from),
            new Square(new Pawn(Color.BLACK), obstacle)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessGame.move(from, to))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
                new Square(new Queen(Color.WHITE), from),
                new Square(new Pawn(Color.BLACK), obstacle));
        });
    }

    private static Stream<Arguments> provideHasObstacleVerticalAndHorizontalWay() {
        return Stream.of(
            Arguments.of(new Position(E, ONE), new Position(E, SEVEN), new Position(E, SIX)),
            Arguments.of(new Position(E, SEVEN), new Position(E, ONE), new Position(E, SIX)),
            Arguments.of(new Position(A, FIVE), new Position(F, FIVE), new Position(E, FIVE)),
            Arguments.of(new Position(F, FIVE), new Position(A, FIVE), new Position(E, FIVE)),
            Arguments.of(new Position(A, EIGHT), new Position(D, FIVE), new Position(C, SIX)),
            Arguments.of(new Position(D, FOUR), new Position(B, SIX), new Position(C, FIVE)),
            Arguments.of(new Position(D, FOUR), new Position(F, SIX), new Position(E, FIVE)),
            Arguments.of(new Position(D, FOUR), new Position(B, TWO), new Position(C, THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideOverObstacle")
    @DisplayName("나이트는 기물을 넘어서 이동 할 수 있다.")
    void moveKnightOverObstacle(Position from, Position to, Position obstacle) {
        ChessGame chessGame = new ChessGame(Set.of(new Square(new Knight(Color.WHITE), from),
            new Square(new Pawn(Color.BLACK), obstacle)), Color.WHITE);

        assertAll(() -> {
            assertThatCode(() -> chessGame.move(from, to)).doesNotThrowAnyException();
            assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
                new Square(new Knight(Color.WHITE), to),
                new Square(new Pawn(Color.BLACK), obstacle));
        });
    }

    private static Stream<Arguments> provideOverObstacle() {
        return Stream.of(
            Arguments.of(new Position(E, FIVE), new Position(C, FOUR), new Position(D, FIVE)),
            Arguments.of(new Position(E, FIVE), new Position(D, SEVEN), new Position(E, SIX)),
            Arguments.of(new Position(E, FIVE), new Position(G, SIX), new Position(F, FIVE)),
            Arguments.of(new Position(E, FIVE), new Position(D, THREE), new Position(E, FOUR))
        );
    }

    @Test
    @DisplayName("폰이 이동하는 위치에 기물이 있으면 예외 발생")
    void throwExceptionMovePawnAlreadyExistPiecePosition() {
        ChessGame chessGame = new ChessGame(Set.of(new Square(new Pawn(Color.WHITE), new Position(D, FOUR)),
            new Square(new Pawn(Color.BLACK), new Position(D, FIVE))), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessGame.move(new Position(D, FOUR), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
            assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
                new Square(new Pawn(Color.WHITE), new Position(D, FOUR)),
                new Square(new Pawn(Color.BLACK), new Position(D, FIVE)));
        });
    }


    @Test
    @DisplayName("폰을 제외한 기물이 이동하는 위치에 기물이 있으면 해당 기물을 제거")
    void removeTargetPieceByMove() {
        ChessGame chessGame = new ChessGame(
            Set.of(new Square(new Queen(Color.WHITE), new Position(D, FOUR)),
                new Square(new Pawn(Color.BLACK), new Position(D, FIVE))), Color.WHITE);

        chessGame.move(new Position(D, FOUR), new Position(D, FIVE));

        assertThat(chessGame.getSquare()).containsExactlyInAnyOrder(
            new Square(new Queen(Color.WHITE), new Position(D, FIVE)));
    }
}
