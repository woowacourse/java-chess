package chess.dto;

import java.util.List;

public class BoardDto {

    private final List<SquareDto> squareDtos;

    public BoardDto(List<SquareDto> squareDtos) {
        this.squareDtos = squareDtos;
    }

    public List<SquareDto> getSquareDtos() {
        return this.squareDtos;
    }
}
