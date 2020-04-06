package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinnerTest {

    @Test
    @DisplayName("승자 구하기")
    void calculateWinnerByScore() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(Winner.getWinners(chessBoard.getChessBoard()).size()).isEqualTo(2);

        chessBoard.movePiece(Square.of("b1"), Square.of("c3"));
        chessBoard.movePiece(Square.of("d7"), Square.of("d5"));
        chessBoard.movePiece(Square.of("c3"), Square.of("d5"));
        assertThat(Winner.getWinners(chessBoard.getChessBoard()).size()).isEqualTo(1);
        assertThat(Winner.getWinners(chessBoard.getChessBoard()).get(0)).isEqualTo(Color.WHITE);
    }
}
