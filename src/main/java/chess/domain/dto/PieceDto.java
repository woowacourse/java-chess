package chess.domain.dto;

import chess.domain.dto.response.ResponseDto;

public final class PieceDto implements ResponseDto {

    private final String position;
    private final String name;

    public PieceDto(final String position, final String name) {
        this.position = position;
        this.name = name;
    }
}
