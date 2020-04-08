package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        List<Square> squares = new ArrayList<>();
        squares.add(Square.of("a2"));
        squares.add(Square.of("a3"));
        chessBoard.movePiece(squares);
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(Square.of("a3"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(Square.of("a3"))).isEqualTo(Pawn.of(Color.WHITE));
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

    @Test
    @DisplayName("새로운 체스보드 상태를 넣어주는 updateChessBoard 테스트")
    void updateChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Square, Piece> updatedBoardInfo = new HashMap<>();
        updatedBoardInfo.put(Square.of("a4"), PieceFactory.of("k"));
        updatedBoardInfo.put(Square.of("c7"), PieceFactory.of("N"));
        updatedBoardInfo.put(Square.of("d5"), PieceFactory.of("n"));
        chessBoard.updateChessBoard(updatedBoardInfo);
        assertThat(chessBoard.getChessBoard()).isEqualTo(updatedBoardInfo);
    }
}
