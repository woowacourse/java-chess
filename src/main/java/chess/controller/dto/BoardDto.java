package chess.controller.dto;

import chess.domain.board.Board;

public class BoardDto {

    private String[][] board;

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(final String[][] board){
        this.board = board;
    }
}
