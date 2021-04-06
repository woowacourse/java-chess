package dto;

import java.util.Objects;

public class StatusDto implements MenuDto {
    private double blackScore;
    private double whiteScore;

    public StatusDto(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusDto statusDto = (StatusDto) o;
        return Double.compare(statusDto.blackScore, blackScore) == 0 && Double.compare(statusDto.whiteScore, whiteScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blackScore, whiteScore);
    }

    @Override
    public boolean isBoardDto() {
        return false;
    }
}
