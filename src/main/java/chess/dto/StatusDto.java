package chess.dto;

import java.util.Objects;

public class StatusDto {

    private final String whiteScore;
    private final String blackScore;
    private final String whiteResult;
    private final String blackResult;

    public StatusDto(double whiteScore, double blackScore, String whiteResult, String blackResult) {
        this.whiteScore = Double.toString(whiteScore);
        this.blackScore = Double.toString(blackScore);
        this.whiteResult = whiteResult;
        this.blackResult = blackResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusDto statusDto = (StatusDto) o;
        return Objects.equals(whiteScore, statusDto.whiteScore) && Objects.equals(blackScore,
                statusDto.blackScore) && Objects.equals(whiteResult, statusDto.whiteResult)
                && Objects.equals(blackResult, statusDto.blackResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore, whiteResult, blackResult);
    }
}
