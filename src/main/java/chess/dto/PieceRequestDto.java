package chess.dto;

public class PieceRequestDto {
    private final String pieceName;
    private final String piecePosition;

    public PieceRequestDto(final String pieceName, final String piecePosition) {
        this.pieceName = pieceName;
        this.piecePosition = piecePosition;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPiecePosition() {
        return piecePosition;
    }
}
