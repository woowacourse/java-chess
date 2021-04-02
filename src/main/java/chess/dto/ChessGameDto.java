package chess.dto;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getPieces() {
        return squareDtos.stream()
            .map(SquareDto::getPiece)
            .collect(Collectors.toList());
    }
}
