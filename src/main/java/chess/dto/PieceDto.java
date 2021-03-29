package chess.dto;

import chess.dto.responsedto.ResponseDto;

public class PieceDto implements ResponseDto {
    private final long pieceId;
    private final boolean isBlack;
    private final String position;
    private final long gridId;
    private final String name;

    public PieceDto(long pieceId, boolean isBlack, String position, long gridId, String name) {
        this.pieceId = pieceId;
        this.isBlack = isBlack;
        this.position = position;
        this.gridId = gridId;
        this.name = name;
    }
}
