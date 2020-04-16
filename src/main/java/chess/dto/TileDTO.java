package chess.dto;

public class TileDTO {
    private static final String STYLE_WHITE = "white";
    private static final String STYLE_BLACK = "black";

    private final String position;
    private final String pieceImageUrl;
    private final String style;

    public TileDTO(final String position, final String pieceImageUrl, final int index) {
        this.position = position;
        this.pieceImageUrl = pieceImageUrl;
        this.style = setStyle(index);
    }

    private String setStyle(int index) {
        if (index % 2 == 0) {
            return STYLE_WHITE;
        }
        return STYLE_BLACK;
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
