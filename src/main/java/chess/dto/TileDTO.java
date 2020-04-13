package chess.dto;

public class TileDTO {
    private static final String STYLE_WHITE = "white";
    private static final String STYLE_BLACK = "black";

    private final String position;
    private String pieceImageUrl;
    private String style;

    public TileDTO(final String position) {
        this.position = position;
    }

    public void setPieceImageUrl(String pieceImageUrl) {
        this.pieceImageUrl = pieceImageUrl;
    }

    public void setStyle(int index) {
        if (index % 2 == 0) {
            this.style = STYLE_WHITE;
            return;
        }
        this.style = STYLE_BLACK;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceImageUrl() {
        return this.pieceImageUrl;
    }

    public String getStyle() {
        return this.style;
    }
}
