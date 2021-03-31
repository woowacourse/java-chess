package chess.dto;

import java.util.List;

public class ChessGameDto {

    private final List<SquareDto> squareDtos;
    private final String state;
    private final ScoreDto score;

    public ChessGameDto(final List<SquareDto> squareDtos, final String state, final ScoreDto score) {
        this.squareDtos = squareDtos;
        this.state = state;
        this.score = score;
    }

    public List<SquareDto> getSquareDtos() {
        return this.squareDtos;
    }

    public String getState() {
        return this.state;
    }

    public ScoreDto getScore() {
        return this.score;
    }
}
