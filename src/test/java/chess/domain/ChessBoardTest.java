package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThatThrownBy(()->chessBoard.canMove(Square.of("c5"), Square.of("c6")))
                .isInstanceOf(UnsupportedOperationException.class);
        //WhiteTurn
        chessBoard.getTurn().changeTurn();
        //Black
        assertThatThrownBy(()->chessBoard.canMove(Square.of("a2"), Square.of("a3")))
                .isInstanceOf(UnsupportedOperationException.class);
        //Black
        assertThat(chessBoard.canMove(Square.of("a7"), Square.of("a6"))).isTrue();
        //WhiteTurn
        assertThatThrownBy(()->chessBoard.canMove(Square.of("a7"), Square.of("b1")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Square.of("a2"),Square.of("a4"));
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a4"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(Square.of("a4"))).isEqualTo(Pawn.of(Color.WHITE));
    }

    @Test
    @DisplayName("king 잡혔는지 확인")
    void isKingOnChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Square.of("e2"), Square.of("e4"));
        chessBoard.movePiece(Square.of("a7"), Square.of("a5"));
        chessBoard.movePiece(Square.of("e1"), Square.of("e2"));
        chessBoard.movePiece(Square.of("a8"), Square.of("a6"));
        chessBoard.movePiece(Square.of("e2"), Square.of("e3"));

        assertThat(chessBoard.isKingCaptured()).isFalse();

        chessBoard.movePiece(Square.of("a6"), Square.of("d6"));
        chessBoard.movePiece(Square.of("e3"), Square.of("d3"));
        chessBoard.movePiece(Square.of("d6"), Square.of("d3"));

        assertThat(chessBoard.isKingCaptured()).isTrue();
    }

}
