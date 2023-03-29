package dto;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardStateDto {
    
    private final List<String> boardState;

    public ChessBoardStateDto(List<String> boardState) {
        this.boardState = new ArrayList<>(boardState);
    }

    public List<String> getBoardState() {
        return boardState;
    }
}
