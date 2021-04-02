package chess.dto;

import java.util.List;

public class ChessGameDto {

    private final List<SquareDto> squareDtos;
    private final String state;

    public ChessGameDto(final List<SquareDto> squareDtos, final String state) {
        this.squareDtos = squareDtos;
        this.state = state;
    }

    public List<SquareDto> getSquareDtos() {
        return squareDtos;
    }

    public String getState() {
        return state;
    }
}
