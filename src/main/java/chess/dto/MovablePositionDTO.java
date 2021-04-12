package chess.dto;

import java.util.List;

public class MovablePositionDTO {

    private List<String> movablePositions;
    private int boardId;
    private String source;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MovablePositionDTO(List<String> positions) {
        movablePositions = positions;
    }

    public List<String> getMovablePositions() {
        return movablePositions;
    }

    public void setMovablePositions(List<String> movablePositions) {
        this.movablePositions = movablePositions;
    }
}
