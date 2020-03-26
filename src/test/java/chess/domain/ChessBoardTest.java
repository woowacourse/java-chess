package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck2() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Square, Piece> board = chessBoard.getChessBoard();
        assertThat(board.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("move 수행이 가능한지 판단")
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        boolean blackTurn = true;
        List<Square> squares = new ArrayList<>();
        squares.add(Square.of("a2"));
        squares.add(Square.of("a3"));
        assertThat(chessBoard.canMove(squares, blackTurn)).isFalse();

        squares.clear();
        squares.add(Square.of("a7"));
        squares.add(Square.of("a6"));
        assertThat(chessBoard.canMove(squares, blackTurn)).isTrue();

        squares.clear();
        squares.add(Square.of("a7"));
        squares.add(Square.of("b1"));
        assertThat(chessBoard.canMove(squares, blackTurn)).isFalse();

    }
}
