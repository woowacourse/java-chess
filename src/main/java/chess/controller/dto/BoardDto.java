package chess.controller.dto;

public class BoardDto {

    private final String[][] board;

    public BoardDto(final String[][] board) {
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }
}
