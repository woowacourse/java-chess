package chess.dto;

public class BoardDto {
    private String position;
    private String pieceName;
    private String team;

    public BoardDto() {
    }

    public BoardDto(final String position, final String pieceName, String team) {
        this.position = position;
        this.pieceName = pieceName;
        this.team = team;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(final String team) {
        this.team = team;
    }
}
