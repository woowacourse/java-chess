package chess;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.Map;
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
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(E, FIVE), new King(Color.WHITE),
                        new Position(E, SEVEN), new King(Color.BLACK)), Color.WHITE);

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FIVE), new Position(F, SIX)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("from과 to 위치가 동일한 경우 예외발생")
    void selectSameFromAndToPosition() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(A, SIX), new King(Color.BLACK),
                        new Position(B, SIX), new King(Color.WHITE),
                        new Position(A, SEVEN), new Pawn(Color.BLACK)), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(A, SEVEN)))
                .isInstanceOf(java.lang.IllegalArgumentException.class);
    }

    @Test
    @DisplayName("움직일 수 없는 위치로 기물을 이동시킬 경우 예외 발생")
    void movePieceToUnmovablePosition() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(A, SEVEN), new Pawn(Color.BLACK)), Color.BLACK);

        assertThatThrownBy(() -> chessBoard.move(new Position(A, SEVEN), new Position(B, SEVEN)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 플레이어가 연속해서 기물을 움직일 경우 예외 발생")
    void throwExceptionWhenMoveSameColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(E, FIVE), new King(Color.WHITE),
                        new Position(B, FIVE), new King(Color.BLACK)), Color.WHITE);

        chessBoard.move(new Position(E, FIVE), new Position(E, SIX));

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, SIX), new Position(E, FIVE)))
                    .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessBoard.getBoard()).isEqualTo(
                    Map.of(new Position(E, SIX), new King(Color.WHITE),
                            new Position(B, FIVE), new King(Color.BLACK)));
        });

    }

    @Test
    @DisplayName("플레이어가 번갈아가며 기물을 움직인다.")
    void moveEachColorPieceSubsequently() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(E, FIVE), new King(Color.WHITE),
                        new Position(B, FIVE), new King(Color.BLACK)), Color.WHITE);

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
                Map.of(new Position(E, FIVE), new King(Color.WHITE),
                        new Position(E, SEVEN), new King(Color.BLACK),
                        new Position(E, SIX), new Pawn(Color.WHITE)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(new Position(E, FIVE), new Position(E, SIX)))
                    .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessBoard.getBoard()).isEqualTo(
                    Map.of(new Position(E, FIVE), new King(Color.WHITE),
                            new Position(E, SEVEN), new King(Color.BLACK),
                            new Position(E, SIX), new Pawn(Color.WHITE)));
        });
    }

    @ParameterizedTest
    @MethodSource("provideHasObstacleVerticalAndHorizontalWay")
    @DisplayName("움직일 때 장애물이 있을 경우 예외 발생")
    void throwExceptionWhenHasObstacleMoveToDestination(Position from, Position to,
                                                        Position obstacle) {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        from, new Queen(Color.WHITE),
                        obstacle, new Pawn(Color.BLACK)), Color.WHITE);

        assertAll(() -> {
            assertThatThrownBy(() -> chessBoard.move(from, to))
                    .isInstanceOf(java.lang.IllegalArgumentException.class);
            assertThat(chessBoard.getBoard()).isEqualTo(
                    Map.of(new Position(H, FIVE), new King(Color.WHITE),
                            new Position(H, SEVEN), new King(Color.BLACK),
                            from, new Queen(Color.WHITE),
                            obstacle, new Pawn(Color.BLACK)));
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
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(A, FIVE), new King(Color.WHITE),
                        new Position(A, SEVEN), new King(Color.BLACK),
                        from, new Knight(Color.WHITE),
                        obstacle, new Pawn(Color.BLACK)), Color.WHITE);

        assertAll(() -> {
            assertThatCode(() -> chessBoard.move(from, to)).doesNotThrowAnyException();
            assertThat(chessBoard.getBoard()).isEqualTo(
                    Map.of(new Position(A, FIVE), new King(Color.WHITE),
                            new Position(A, SEVEN), new King(Color.BLACK),
                            to, new Knight(Color.WHITE),
                            obstacle, new Pawn(Color.BLACK)));
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
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(D, FOUR), new Queen(Color.WHITE),
                        new Position(D, FIVE), new Pawn(Color.BLACK)), Color.WHITE);

        chessBoard.move(new Position(D, FOUR), new Position(D, FIVE));

        assertThat(chessBoard.getBoard()).isEqualTo(
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(D, FIVE), new Queen(Color.WHITE)));
    }

    @Test
    @DisplayName("폰은 대각선에 위치한 기물을 제거할 수 있다.")
    void removeTargetPieceByPawn() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(D, FOUR), new Pawn(Color.WHITE),
                        new Position(E, FIVE), new Pawn(Color.BLACK)), Color.WHITE);

        chessBoard.move(new Position(D, FOUR), new Position(E, FIVE));

        assertThat(chessBoard.getBoard()).isEqualTo(
                Map.of(new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(E, FIVE), new Pawn(Color.WHITE)));
    }

    @Test
    @DisplayName("폰은 앞으로 이동할 때 기물이 있으면 움직일 수 없다.")
    void throwExceptionWhenPawnVerticallyMoveToHasTargetPiece() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(
                        new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(D, FOUR), new Pawn(Color.WHITE),
                        new Position(D, FIVE), new Pawn(Color.BLACK)), Color.WHITE);

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FOUR), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 대각선으로 이동할 때 기물이 없으면 이동할 수 없다.")
    void throwExceptionWhenPawnDiagonalMoveToNotHasTargetPiece() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(
                        new Position(H, FIVE), new King(Color.WHITE),
                        new Position(H, SEVEN), new King(Color.BLACK),
                        new Position(D, FOUR), new Pawn(Color.WHITE),
                        new Position(D, FIVE), new Pawn(Color.BLACK)), Color.WHITE);

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FOUR), new Position(C, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("기물들 기본 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(D, FOUR), new Pawn(Color.WHITE),
                        new Position(D, FIVE), new Rook(Color.WHITE),
                        new Position(D, SIX), new Bishop(Color.WHITE),
                        new Position(D, SEVEN), new Knight(Color.WHITE),
                        new Position(D, EIGHT), new Queen(Color.WHITE),
                        new Position(D, ONE), new King(Color.WHITE),
                        new Position(E, ONE), new King(Color.BLACK)), Color.WHITE);

        assertAll(() -> {
            assertThat(chessBoard.getScore(Color.WHITE).getValue())
                    .isEqualTo(new BigDecimal("20.5"));
            assertThat(chessBoard.getScore(Color.BLACK).getValue())
                    .isEqualTo(new BigDecimal("0.0"));
        });
    }

    @Test
    @DisplayName("동일한 기물들 기본 점수 계산")
    void calculateSameFilePawnScore() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(
                        new Position(A, ONE), new King(Color.WHITE),
                        new Position(D, FOUR), new Pawn(Color.WHITE),
                        new Position(D, FIVE), new Pawn(Color.WHITE),
                        new Position(D, SIX), new Pawn(Color.WHITE),
                        new Position(D, SEVEN), new Rook(Color.WHITE),
                        new Position(A, TWO), new King(Color.BLACK),
                        new Position(D, THREE), new Pawn(Color.BLACK),
                        new Position(E, THREE), new Pawn(Color.BLACK)), Color.WHITE);

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
                Map.of(new Position(D, FOUR), new King(Color.WHITE),
                        new Position(D, FIVE), new King(Color.BLACK)), Color.WHITE);

        chessBoard.move(new Position(D, FOUR), new Position(D, FIVE));

        assertAll(() -> {
            assertThat(chessBoard.isFinished()).isTrue();
            assertThat(chessBoard.getWinner()).isEqualTo(Color.WHITE);
        });
    }

    @Test
    @DisplayName("게임이 종료된 후 체스 이동 불가")
    void throwExceptionWhenMovePieceAfterEndGame() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(D, FOUR), new King(Color.WHITE),
                        new Position(D, FIVE), new King(Color.BLACK)),
                Color.WHITE
        );

        chessBoard.move(new Position(D, FOUR), new Position(D, FIVE));

        assertThatThrownBy(() -> chessBoard.move(new Position(D, FIVE), new Position(D, SIX)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("동일한 위치의 기물이 있는 경우 예외 발생")
    void throwExceptionWhenHasSamePositionPieces() {
        assertThatThrownBy(() ->
                new ChessBoard(Map.of(
                        new Position(D, FOUR), new King(Color.WHITE),
                        new Position(D, FOUR), new King(Color.BLACK)), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 색깔 별로 킹이 없는 경우 예외 발생")
    void throwExceptionWhenNotHasEachColorKing() {
        assertThatThrownBy(() ->
                new ChessBoard(Map.of(
                        new Position(D, FOUR), new King(Color.WHITE)), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("체스 게임 종료 전에 승자 확인 시 예외 발생")
    void throwExceptionWhenGetWinnerBeforeFinishGame() {
        ChessBoard chessBoard = new ChessBoard(
                Map.of(new Position(D, FOUR), new King(Color.WHITE),
                        new Position(D, FIVE), new King(Color.BLACK)),
                Color.WHITE
        );

        assertThatThrownBy(() -> chessBoard.getWinner())
                .isInstanceOf(IllegalStateException.class);
    }
}
