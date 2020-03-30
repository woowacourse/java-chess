package chess.domain2;

import chess.domain2.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard().size()).isEqualTo(32);
    }

    @Test
    @DisplayName("move 수행이 가능한지 판단")
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.canMove(Square.of("c5"), Square.of("c6"))).isFalse();
        //WhiteTurn
        chessBoard.changeTurn();
        //Black
        assertThat(chessBoard.canMove(Square.of("a2"), Square.of("a3"))).isFalse();
        //Black
        assertThat(chessBoard.canMove(Square.of("a7"), Square.of("a6"))).isTrue();
        //WhiteTurn
        assertThat(chessBoard.canMove(Square.of("a7"), Square.of("b1"))).isFalse();
    }

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        List<Square> squares = new ArrayList<>();
        squares.add(Square.of("a2"));
        squares.add(Square.of("a4"));
        chessBoard.movePiece(squares);
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a4"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(Square.of("a4"))).isEqualTo(Pawn.of(Color.WHITE));
    }

    @Test
    @DisplayName("king 잡혔는지 확인")
    void isKingOnChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Arrays.asList(Square.of("e2"), Square.of("e4")));
        chessBoard.movePiece(Arrays.asList(Square.of("a7"), Square.of("a5")));
        chessBoard.movePiece(Arrays.asList(Square.of("e1"), Square.of("e2")));
        chessBoard.movePiece(Arrays.asList(Square.of("a8"), Square.of("a6")));
        chessBoard.movePiece(Arrays.asList(Square.of("e2"), Square.of("e3")));

        assertThat(chessBoard.isKingCaptured()).isFalse();

        chessBoard.movePiece(Arrays.asList(Square.of("a6"), Square.of("d6")));
        chessBoard.movePiece(Arrays.asList(Square.of("e3"), Square.of("d3")));
        chessBoard.movePiece(Arrays.asList(Square.of("d6"), Square.of("d3")));

        assertThat(chessBoard.isKingCaptured()).isTrue();
    }
}
