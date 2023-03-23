package chess.controller.mapper;

import chess.controller.dto.PositionDto;

public final class PositionDtoMapper {

    public static PositionDto createPositionDto(final int rank, final int file) {
        return new PositionDto(rank, file);
    }
}
