package chess;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.*;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessBoardTest {

    @Test
    @DisplayName("움직이려는 위치에 기물이 존재하지 않으면 예외 발생")
    void selectedNotFoundPieces() {
        ChessBoard chessBoard = new ChessBoard(List.of(), Color.WHITE);

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FIVE), new Position(F, SIX)))
            .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("from과 to 위치가 동일한 경우 예외발생")
    void selectSameFromAndToPosition() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, SEVEN)))
            .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 위치로 기물을 이동시킬 경우 예외 발생")
    void movePieceToUnmovablePosition() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.BLACK, new Position(A, SEVEN))), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(B, SEVEN)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new King(Color.WHITE, new Position(E, FIVE)),
                new King(Color.BLACK, new Position(B, FIVE))), Color.WHITE);

        chessBoard.move(new Position(E, FIVE), new Position(E, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, SIX), new Position(E, FIVE)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                new King(Color.WHITE, new Position(E, SIX)),
                new King(Color.BLACK, new Position(B, FIVE)));
        });

    }

    @Test
    @DisplayName("플레이어가 번갈아가며 기물을 움직인다.")
    void moveEachColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new King(Color.WHITE, new Position(E, FIVE)),
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
        ChessBoard chessBoard = new ChessBoard(
            List.of(new King(Color.WHITE, new Position(E, FIVE)),
                new Pawn(Color.WHITE, new Position(E, SIX))), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, FIVE), new Position(E, SIX)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
                new King(Color.WHITE, new Position(E, FIVE)),
                new Pawn(Color.WHITE, new Position(E, SIX)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideHasObstacleVerticalAndHorizontalWay")
    @DisplayName("움직일 때 장애물이 있을 경우 예외 발생")
    void throwExceptionWhenHasObstacleMoveToDestination(Position from, Position to,
        Position obstacle) {
        ChessBoard chessBoard = new ChessBoard(List.of(new Queen(Color.WHITE, from),
            new Pawn(Color.BLACK, obstacle)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
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

        assertAll(() -> {
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

    @Test
    @DisplayName("폰을 제외한 기물이 이동하는 위치에 기물이 있으면 해당 기물을 제거")
    void removeTargetPieceByMove() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Queen(Color.WHITE, new Position(D, FOUR)),
                new Pawn(Color.BLACK, new Position(D, FIVE))), Color.WHITE);

        chessBoard.move(new Position(D, FOUR), new Position(D, FIVE));

        assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
            new Queen(Color.WHITE, new Position(D, FIVE)));
    }

    @Test
    @DisplayName("폰은 대각선에 위치한 기물을 제거할 수 있다.")
    void removeTargetPieceByPawn() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.WHITE, new Position(D, FOUR)),
                new Pawn(Color.BLACK, new Position(E, FIVE))), Color.WHITE);

        chessBoard.move(new Position(D, FOUR), new Position(E, FIVE));

        assertThat(chessBoard.getPieces()).containsExactlyInAnyOrder(
            new Pawn(Color.WHITE, new Position(E, FIVE))
        );
    }

    @Test
    @DisplayName("기물들 기본 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.WHITE, new Position(D, FOUR)),
                new Rook(Color.WHITE, new Position(D, FIVE)),
                new Bishop(Color.WHITE, new Position(D, SIX)),
                new Knight(Color.WHITE, new Position(D, SEVEN)),
                new Queen(Color.WHITE, new Position(D, EIGHT)),
                new King(Color.WHITE, new Position(D, ONE)),
                new Pawn(Color.BLACK, new Position(E, EIGHT)),
                new Rook(Color.BLACK, new Position(E, THREE)),
                new Bishop(Color.BLACK, new Position(E, SEVEN)),
                new Knight(Color.BLACK, new Position(E, SIX)),
                new Queen(Color.BLACK, new Position(E, FIVE)),
                new King(Color.BLACK, new Position(E, FOUR))), Color.WHITE);

        assertAll(() -> {
            assertThat(chessBoard.getScore(Color.WHITE).getValue())
                .isEqualTo(new BigDecimal("20.5"));
            assertThat(chessBoard.getScore(Color.BLACK).getValue())
                .isEqualTo(new BigDecimal("20.5"));
        });
    }

    @Test
    @DisplayName("동일한 기물들 기본 점수 계산")
    void calculateSameFilePawnScore() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new Pawn(Color.WHITE, new Position(D, FOUR)),
                new Pawn(Color.WHITE, new Position(D, FIVE)),
                new Pawn(Color.WHITE, new Position(D, SIX)),
                new Rook(Color.WHITE, new Position(D, SEVEN)),
                new Pawn(Color.BLACK, new Position(D, THREE)),
                new Pawn(Color.BLACK, new Position(E, THREE))), Color.WHITE);

        assertAll(() -> {
            assertThat(chessBoard.getScore(Color.WHITE).getValue())
                .isEqualTo(new BigDecimal("6.5"));
            assertThat(chessBoard.getScore(Color.BLACK).getValue())
                .isEqualTo(new BigDecimal("2.0"));
        });
    }

    @Test
    @DisplayName("상대방 킹이 없다면 게임 종료")
    void gameEndWhenKingCaptured() {
        ChessBoard chessBoard = new ChessBoard(
            List.of(new King(Color.WHITE, new Position(D, FOUR))), Color.WHITE);

        assertAll(() -> {
            assertThat(chessBoard.isFinished()).isTrue();
            assertThat(chessBoard.getWinner()).isEqualTo(Color.WHITE);
        });
    }
}
