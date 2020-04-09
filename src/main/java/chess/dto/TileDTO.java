package chess.dto;

public class TileDTO {
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    private final String position;
    private String pieceImageUrl;
    private String style;

    public TileDTO(final String position) {
        this.position = position;
        this.pieceImageUrl = "";
    }

    public void setStyle(int index) {
        if (index % 2 == 0) {
            this.style = WHITE;
            return;
        }
        this.style = BLACK;
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
}
