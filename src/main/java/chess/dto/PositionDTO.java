package chess.dto;

import chess.model.position.Position;

public record PositionDTO(int file, int rank) {
    public Position toEntity() {
        return Position.from(file(), rank());
    }
}
