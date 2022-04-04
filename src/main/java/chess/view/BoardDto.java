package chess.view;

import java.util.List;

public class BoardDto {
    private final List<PositionDto> positionDtos;

    public BoardDto(List<PositionDto> positionDtos) {
        this.positionDtos = positionDtos;
    }

    public List<PositionDto> getPositionDtos() {
        return positionDtos;
    }
}
