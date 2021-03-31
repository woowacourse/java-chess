package chess.dto;

import java.util.List;

public class BoardDto {

    private final List<SquareDto> squareDtos;
    private final String state;

    public BoardDto(final List<SquareDto> squareDtos, final String state) {
        this.squareDtos = squareDtos;
        this.state = state;
    }

    public List<SquareDto> getSquareDtos() {
        return this.squareDtos;
    }

    public String getState() {
        return state;
    }
}
