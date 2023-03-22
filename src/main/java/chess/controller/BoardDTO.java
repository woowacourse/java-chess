package chess.controller;

public final class BoardDTO {
    
    private final String board;
    
    private BoardDTO(String board) {
        this.board = board;
    }
    
    public static BoardDTO create(String board) {
        return new BoardDTO(board);
    }
    
    public String getBoard() {
        return this.board;
    }
}
