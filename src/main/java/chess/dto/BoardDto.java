package chess.dto;

public class BoardDto {
    private String position;
    private String pieceName;

    public BoardDto() {}

    public BoardDto(final String position, final String pieceName) {
        this.position = position;
        this.pieceName = pieceName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(final String pieceName) {
        this.pieceName = pieceName;
    }
}
