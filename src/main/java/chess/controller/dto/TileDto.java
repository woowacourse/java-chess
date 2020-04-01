package chess.controller.dto;

public class TileDto {
    private final String position;
    private final String pieceImageUrl;

    public TileDto(final String position, final String pieceImageUrl) {
        this.position = position;
        this.pieceImageUrl = pieceImageUrl;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceImageUrl() {
        return pieceImageUrl;
    }
}
