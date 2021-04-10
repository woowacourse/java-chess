package chess.dto;

import java.util.List;

public class MovableResponseDto {

    private final List<PositionDto> positionDtos;

    public MovableResponseDto(final List<PositionDto> positionDtos) {
        this.positionDtos = positionDtos;
    }

    public List<PositionDto> getPositionDtos() {
        return this.positionDtos;
    }
}
