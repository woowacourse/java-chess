package chess;

import chess.piece.Empty;
import chess.piece.Knight;
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
        List<Integer> sourcePosition = List.of(2, 1);

        assertThat(chessGame.findChessPiece(sourcePosition)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("현재 위치에 기물을 공백으로 변경한다.")
    void shouldSuccessChangeEmpty() {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        List<Integer> sourcePosition = List.of(2, 1);

        chessGame.removeChessPiece(sourcePosition);

        assertThat(chessBoard.getChessPieceByPosition(sourcePosition)).isInstanceOf(Empty.class);
    }

    @Test
    @DisplayName("목표 위치에 기물을 옮바르게 변경한다.")
    void shouldSuccessMoveChessPiece() {

        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Knight knight = new Knight(Side.WHITE);
        List<Integer> targetPosition = List.of(1, 3);

        chessGame.copyChessPiece(knight, targetPosition);

        assertThat(chessBoard.getChessPieceByPosition(targetPosition)).isEqualTo(knight);
    }
}
