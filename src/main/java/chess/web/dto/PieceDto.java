package chess.web.dto;

public class PieceDto {

    private final String id;
    private final PieceType pieceType;

    public PieceDto(String id, PieceType pieceType) {
        this.id = id;
        this.pieceType = pieceType;
    }

    public String getId() {
        return id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
