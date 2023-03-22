package domain.game;

import java.util.Objects;

public class Movement {
    private static final int NON_INCREMENT = 0;
    private final int fileIncrement;
    private final int rankIncrement;

    public Movement(int fileIncrement, int rankIncrement) {
        validate(fileIncrement, rankIncrement);
        this.fileIncrement = fileIncrement;
        this.rankIncrement = rankIncrement;
    }

    private void validate(int fileIncrement, int rankIncrement) {
        if (fileIncrement == NON_INCREMENT && rankIncrement == NON_INCREMENT) {
            throw new IllegalArgumentException("최소 한 칸은 움직여야 합니다.");
        }
    }

    public boolean isUpward() {
        return this.rankIncrement > 0;
    }

    public boolean isDownward() {
        return this.rankIncrement < 0;
    }

    public boolean isRight() {
        return this.fileIncrement > 0;
    }

    public boolean isLeft() {
        return this.fileIncrement < 0;
    }

    public boolean isPerpendicular() {
        return this.rankIncrement == NON_INCREMENT || this.fileIncrement == NON_INCREMENT;
    }

    public boolean isDiagonal() {
        return Math.abs(this.rankIncrement) == Math.abs(this.fileIncrement);
    }

    public boolean isOneStep() {
        return Math.abs(fileIncrement) <= 1 && Math.abs(rankIncrement) <= 1;
    }

    public boolean isUnderTwoSteps() {
        return Math.abs(fileIncrement) <= 2 && Math.abs(rankIncrement) <= 2;
    }

    public boolean isThreeStepAndNotPerpendicular() {
        return Math.abs(this.fileIncrement * this.rankIncrement) == 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movement movement = (Movement) o;
        return fileIncrement == movement.fileIncrement && rankIncrement == movement.rankIncrement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileIncrement, rankIncrement);
    }
}
