package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {
    @Test
    @DisplayName("체스보드 생성시 32개의 칸-말 셋트를 가지고 있는지 확인")
    void chessBoardSizeCheck2() {
        ChessBoard chessBoard = new ChessBoard();
        Map<BoardSquare, Piece> board = chessBoard.getChessBoard();
        assertThat(board.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("move 수행이 가능한지 판단")
    void canMove() {
        ChessBoard chessBoard = new ChessBoard();
        boolean blackTurn = true;
        List<BoardSquare> boardSquares = new ArrayList<>();
        boardSquares.add(BoardSquare.of("a2"));
        boardSquares.add(BoardSquare.of("a3"));
        assertThat(chessBoard.movePieceWhenCanMove(boardSquares, blackTurn)).isFalse();

        boardSquares.clear();
        boardSquares.add(BoardSquare.of("a7"));
        boardSquares.add(BoardSquare.of("a6"));
        assertThat(chessBoard.movePieceWhenCanMove(boardSquares, blackTurn)).isTrue();

        boardSquares.clear();
        boardSquares.add(BoardSquare.of("a7"));
        boardSquares.add(BoardSquare.of("b1"));
        assertThat(chessBoard.movePieceWhenCanMove(boardSquares, blackTurn)).isFalse();
    }

    @Test
    @DisplayName("move 수행 테스트")
    void move() {
        ChessBoard chessBoard = new ChessBoard();
        List<BoardSquare> boardSquares = new ArrayList<>();
        boardSquares.add(BoardSquare.of("a2"));
        boardSquares.add(BoardSquare.of("a3"));
        chessBoard.movePiece(boardSquares);
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a2"))).isFalse();
        assertThat(chessBoard.getChessBoard().containsKey(BoardSquare.of("a3"))).isTrue();
        assertThat(chessBoard.getChessBoard().get(BoardSquare.of("a3"))).isEqualTo(Pawn.getPieceInstance(Color.WHITE));
    }

    @Test
    @DisplayName("king 잡혔는지 확인")
    void isKingOnChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("e2"), BoardSquare.of("e4")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("a7"), BoardSquare.of("a5")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("e1"), BoardSquare.of("e2")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("a8"), BoardSquare.of("a6")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("e2"), BoardSquare.of("e3")));

        assertThat(chessBoard.isKingCaptured()).isFalse();

        chessBoard.movePiece(Arrays.asList(BoardSquare.of("a6"), BoardSquare.of("d6")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("e3"), BoardSquare.of("d3")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("d6"), BoardSquare.of("d3")));

        assertThat(chessBoard.isKingCaptured()).isTrue();
    }

    @Test
    @DisplayName("게임 점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = new ChessBoard();
        Map<Color, Double> teamScore = chessBoard.getTeamScore();
        assertThat(teamScore.get(Color.BLACK)).isEqualTo(38);
        assertThat(teamScore.get(Color.WHITE)).isEqualTo(38);

        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c2"), BoardSquare.of("c4")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("d7"), BoardSquare.of("d5")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c4"), BoardSquare.of("d5")));

        teamScore = chessBoard.getTeamScore();
        assertThat(teamScore.get(Color.BLACK)).isEqualTo(37);
        assertThat(teamScore.get(Color.WHITE)).isEqualTo(37);
    }

    @Test
    @DisplayName("승자 구하기")
    void calculateWinnerByScore() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getWinners().size()).isEqualTo(2);

        chessBoard.movePiece(Arrays.asList(BoardSquare.of("b1"), BoardSquare.of("c3")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("d7"), BoardSquare.of("d5")));
        chessBoard.movePiece(Arrays.asList(BoardSquare.of("c3"), BoardSquare.of("d5")));
        assertThat(chessBoard.getWinners().size()).isEqualTo(1);
        assertThat(chessBoard.getWinners().get(0)).isEqualTo(Color.WHITE);
    }
}
