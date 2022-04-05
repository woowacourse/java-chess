package chess.dto;

import java.util.Map;

public class SquareDto {

    private Map<String, String> squares;

    public SquareDto(final Map<String, String> squares) {
        this.squares = squares;
    }

    public Map<String, String> getSquares() {
        return squares;
    }
}
