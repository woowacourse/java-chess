package chess.dto;

public class TileDTO {
    private static final String STYLE_WHITE = "white";
    private static final String STYLE_BLACK = "black";

    private final String position;
    private String team;
    private String pieceType;
    private String pieceImageUrl;
    private String style;

    public TileDTO(final String position) {
        this.position = position;
        this.pieceImageUrl = "";
    }

    public void setStyle(int index) {
        if (index % 2 == 0) {
            this.style = STYLE_WHITE;
            return;
        }
        this.style = STYLE_BLACK;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public void setPieceImageUrl(String pieceImageUrl) {
        this.pieceImageUrl = pieceImageUrl;
    }

    public String getPosition() {
        return position;
    }

    public String getStyle() {
        return this.style;
    }

    public String getPieceImageUrl() {
        return this.pieceImageUrl;
    }

    public String getPieceType() {
        return this.pieceType;
    }

    public String getTeam() {
        return this.team;
    }
}
