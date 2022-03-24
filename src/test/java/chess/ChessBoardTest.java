package chess;

import static chess.position.Rank.*;
import static chess.position.File.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.exception.HasObstacleException;
import chess.exception.UnmovableException;
import chess.piece.*;
import chess.position.Position;
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

    @Test
    @DisplayName("움직일 수 없는 위치로 기물을 이동시킬 경우 예외 발생")
    void movePieceToUnmovablePosition() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(B, SEVEN)))
            .isInstanceOf(UnmovableException.class);
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

    @ParameterizedTest
    @MethodSource("provideHasObstacleVerticalAndHorizontalWay")
    @DisplayName("움직일 때 장애물이 있을 경우 예외 발생")
    void throwExceptionWhenHasObstacleMoveToDestination(Position from, Position to, Position obstacle) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.WHITE, from),
            new Pawn(Color.BLACK, obstacle)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(HasObstacleException.class);
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                    new Queen(Color.WHITE, from),
                    new Pawn(Color.BLACK, obstacle));
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
        ChessBoard chessBoard = new ChessBoard(List.of(new Knight(Color.WHITE, from),
            new Pawn(Color.BLACK, obstacle)), Color.WHITE);

        assertAll(()->{
            assertThatCode(() -> chessBoard.move(from, to)).doesNotThrowAnyException();
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                new Knight(Color.WHITE, to),
                new Pawn(Color.BLACK, obstacle));
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
}
