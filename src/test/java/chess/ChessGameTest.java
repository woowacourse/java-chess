package chess;

import chess.piece.Empty;
import chess.piece.Knight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

        assertThat(chessGame.findChessPiece(sourcePosition)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("현재 위치에 기물을 공백으로 변경한다.")
    void shouldSuccessChangeEmpty() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.initPosition(2, 1);

        chessGame.removeChessPiece(sourcePosition);

        assertThat(chessBoard.getChessPieceByPosition(sourcePosition)).isInstanceOf(Empty.class);
    }

    @Test
    @DisplayName("목표 위치에 기물을 옮바르게 변경한다.")
    void shouldSuccessMoveChessPiece() {

        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Knight knight = new Knight(Side.WHITE);
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
        List<Position> movablePosition = List.of(Position.initPosition(3, 3), Position.initPosition(1, 3), Position.initPosition(2, 2), Position.initPosition(2, 4));

        Assertions.assertThatThrownBy(() -> chessGame.validateMovablePosition(targetPosition, movablePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로는 이동할 수 없습니다.");
    }
}
