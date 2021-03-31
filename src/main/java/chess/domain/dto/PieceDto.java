package chess.domain.dto;

import chess.domain.dto.response.ResponseDto;

public class PieceDto implements ResponseDto {

    private final long pieceId;
    private final boolean isBlack;
    private final String position;
    private final String name;

    public PieceDto(final String position, final String name) {
        this(0, true, position, name);
    }

    public PieceDto(final long pieceId, final boolean isBlack, final String position, final String name) {
        this.pieceId = pieceId;
        this.isBlack = isBlack;
        this.position = position;
        this.name = name;
    }
}
