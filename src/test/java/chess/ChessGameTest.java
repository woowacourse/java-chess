package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.piece.ChessPiece;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.MovablePosition;
import chess.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessGameTest {

    @Test
    @DisplayName("체스 게임을 올바르게 생성한다.")
    void shouldSuccessGenerateChessGame() {
        assertDoesNotThrow(() -> new ChessGame(ChessBoard.generateChessBoard()));
    }

    @Test
    @DisplayName("현재 위치에 기물을 찾는다.")
    void shouldSuccessFindChessPiece() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.initPosition(2, 1);

        assertThat(chessGame.findChessPiece(sourcePosition).getShape()).isEqualTo(Shape.KNIGHT);
    }

    @Test
    @DisplayName("현재 위치에 기물을 공백으로 변경한다.")
    void shouldSuccessChangeEmpty() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.initPosition(2, 1);

        chessGame.removeChessPiece(sourcePosition);

        assertThat(chessBoard.getChessPieceByPosition(sourcePosition).getShape()).isEqualTo(Shape.EMPTY);
    }

    @Test
    @DisplayName("목표 위치에 기물을 옮바르게 변경한다.")
    void shouldSuccessMoveChessPiece() {

        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessPiece knight = new Knight(Side.BLACK);
        Position targetPosition = Position.initPosition(1, 3);

        chessGame.copyChessPiece(knight, targetPosition);

        assertThat(chessBoard.getChessPieceByPosition(targetPosition)).isEqualTo(knight);
    }

    @Test
    @DisplayName("목표 위치가 이동 가능한 영역에 포함되지 않으면 예외를 발생한다.")
    void shouldFailTargetPositionInMovablePosition() {

        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position targetPosition = Position.initPosition(5, 5);
        MovablePosition movablePosition = new MovablePosition(
                List.of(Position.initPosition(3, 3), Position.initPosition(1, 3),
                        Position.initPosition(2, 2), Position.initPosition(2, 4)));

        assertThatThrownBy(() -> chessGame.validateMovablePosition(targetPosition, movablePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"e1, true", "e8, true", "d1, false", "d8, false", "a2, false"})
    @DisplayName("체스판에서 King의 소멸 여부를 체크한다.")
    void checkKingIsDead(String removePostion, boolean isEnd) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        chessGame.removeChessPiece(Position.of(removePostion));
        assertThat(chessGame.isGameEnd(chessBoard)).isEqualTo(isEnd);
    }

    @Test
    @DisplayName("해당 사이드의 점수를 계산한다.")
    void shouldSuccessCalculateScore() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);

        assertAll(
                () -> assertThat(chessGame.takeScore(Side.BLACK)).isEqualTo(38),
                () -> assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(38),
                () -> {
                    chessGame.removeChessPiece(Position.of("a1"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(33);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("b1"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(30.5);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("c1"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(27.5);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("d1"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(18.5);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("e1"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(18.5);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("a2"));
                    chessGame.copyChessPiece(new Pawn(Side.WHITE), Position.of("b3"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(17.5);
                },
                () -> {
                    chessGame.removeChessPiece(Position.of("c2"));
                    chessGame.copyChessPiece(new Pawn(Side.WHITE), Position.of("b4"));
                    assertThat(chessGame.takeScore(Side.WHITE)).isEqualTo(17);
                }
        );
    }
}
