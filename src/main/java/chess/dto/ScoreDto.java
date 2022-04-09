package chess.dto;

import java.util.Objects;

public class ScoreDto {
    private final double whiteScore;
    private final double blackScore;

    public ScoreDto(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ScoreDto scoreDto = (ScoreDto) o;
        return Double.compare(scoreDto.whiteScore, whiteScore) == 0
                && Double.compare(scoreDto.blackScore, blackScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore);
    }
}
