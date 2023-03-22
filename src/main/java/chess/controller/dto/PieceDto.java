package chess.controller.dto;

public final class PieceDto {
    private final String pieceType;
    private final String campType;

    public PieceDto(final String pieceType, final String campType) {
        this.pieceType = pieceType;
        this.campType = campType;
    }

    public boolean isSameCamp(final String campType) {
        return this.campType.equals(campType);
    }

    public String getPieceType() {
        return pieceType;
    }
}
