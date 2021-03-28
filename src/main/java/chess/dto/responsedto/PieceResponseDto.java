package chess.dto.responsedto;

public class PieceResponseDto implements ResponseDto {
    private final long pieceId;
    private final boolean isBlack;
    private final String position;
    private final long gridId;
    private final String name;

    public PieceResponseDto(long pieceId, boolean isBlack, String position, long gridId, String name) {
        this.pieceId = pieceId;
        this.isBlack = isBlack;
        this.position = position;
        this.gridId = gridId;
        this.name = name;
    }
}
