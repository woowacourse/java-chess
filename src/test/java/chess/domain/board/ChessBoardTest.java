package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.state.MoveSquare;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck() {
        ChessBoard chessBoard = new ChessBoard();
        Map<BoardSquare, Piece> board = chessBoard.getChessBoard();
        assertThat(board.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("move 수행이 가능한지 판단")
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a6"))).isFalse();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a3"))).isTrue();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a6"))).isTrue();
        assertThat(chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "b1"))).isFalse();
    }

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("a2", "a3"));
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a3"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(BoardSquare.of("a3")))
            .isEqualTo(Pawn.getPieceInstance(Color.WHITE));
    }

    @Test
    @DisplayName("king 잡혔는지 확인")
    void isKingOnChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePieceWhenCanMove(new MoveSquare("e2", "e4"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a7", "a5"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e1", "e2"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("a8", "a6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e2", "e3"));

        assertThat(chessBoard.isKingCaptured()).isFalse();

        chessBoard.movePieceWhenCanMove(new MoveSquare("a6", "d6"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("e3", "d3"));
        chessBoard.movePieceWhenCanMove(new MoveSquare("d6", "d3"));

        assertThat(chessBoard.isKingCaptured()).isTrue();
    }
}
