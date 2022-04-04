package chess.dto;

import java.util.Map;

public class BoardMapDto {

    private final Map<String, Object> boardMap;


    public BoardMapDto(Map<String, Object> boardMap) {
        this.boardMap = boardMap;
    }

    public Map<String, Object> getBoardMap() {
        return boardMap;
    }
}
