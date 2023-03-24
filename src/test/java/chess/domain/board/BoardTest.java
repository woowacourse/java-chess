package chess.domain.board;

import chess.controller.state.BoardDTO;
import chess.domain.piece.Color;
import chess.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.board.Positions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardTest {
    @DisplayName("file1에 whitePawn이 1개이면 전체는 38점")
    @Test
    void calculateFileScore() {
       var board = new Board();

        System.out.println("white : " + board.calculateTotalScore(Color.WHITE));

        new OutputView().printBoard(new BoardDTO(board.getSquares()));

        assertThat(board.calculateTotalScore(Color.WHITE)).isEqualTo(38);
    }

    @DisplayName("file1에 whitePawn이 2개이면 전체는 37점")
    @Test
    void calculateFileScore2() {
        var board = new Board();

        board.play(B2, B4, Color.WHITE);
        board.play(A7, A5, Color.BLACK);
        board.play(B4, A5, Color.WHITE);

        assertThat(board.calculateTotalScore(Color.WHITE)).isEqualTo(37);
    }

    @DisplayName("file1에 whitePawn이 3개이면 전체는 36.5점")
    @Test
    void calculateFileScore3() {
        var board = new Board();

        board.play(B2, B4, Color.WHITE);
        board.play(A7, A5, Color.BLACK);
        board.play(B4, A5, Color.WHITE);
        board.play(B7, B5, Color.BLACK);
        board.play(C2, C4, Color.WHITE);
        board.play(A8, A6, Color.BLACK);
        board.play(C4, B5, Color.WHITE);
        board.play(C7, C6, Color.BLACK);
        board.play(B5, A6, Color.WHITE);

        assertThat(board.calculateTotalScore(Color.WHITE)).isEqualTo(38);
    }
}
