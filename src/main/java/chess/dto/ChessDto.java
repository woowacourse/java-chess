package chess.dto;

public class ChessDto {
    boolean isGameOver;
    BoardDto boardDto;

    public ChessDto(boolean isGameOver, BoardDto boardDto) {
        this.isGameOver = isGameOver;
        this.boardDto = boardDto;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }

    public void setBoardDto(BoardDto boardDto) {
        this.boardDto = boardDto;
    }
}
