package chess.dto;

public class MovePieceDTO {

    private int boardId;
    private String source;
    private String target;

    public MovePieceDTO() {
    }

    public MovePieceDTO(final int boardId, final String source, final String target) {
        this.boardId = boardId;
        this.source = source;
        this.target = target;
    }

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
