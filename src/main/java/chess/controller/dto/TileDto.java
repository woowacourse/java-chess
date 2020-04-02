package chess.controller.dto;

public class TileDto {
    private final String position;
    private String pieceImageUrl;
    private String team;

    public TileDto(final String position) {
        this.position = position;
        this.pieceImageUrl = "";
    }

    public void setPieceImageUrl(String pieceImageUrl) {
        this.pieceImageUrl = pieceImageUrl;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return this.team;
    }

    public String getPieceImageUrl() {
        return this.pieceImageUrl;
    }
}
