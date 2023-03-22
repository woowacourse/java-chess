package chess.service;

import chess.controller.dto.PositionDto;

public final class PositionService {
    
    public PositionDto createPositionDto(final int rank, final int file) {
        return new PositionDto(rank, file);
    }
}
