package dto;

import java.util.List;

public class GameInfoDto {
    private final String currentTurn;
    private final List<BoardDto> boardDtos;

    public GameInfoDto(String currentTurn, List<BoardDto> boardDtos) {
        this.currentTurn = currentTurn;
        this.boardDtos = boardDtos;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public List<BoardDto> getBoardDtos() {
        return boardDtos;
    }
}
