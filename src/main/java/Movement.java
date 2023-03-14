import java.util.Objects;

public class Movement {
    private static final int STOP = 0;
    private final int fileIncrement;
    private final int rankIncrement;

    public Movement(int fileIncrement, int rankIncrement) {
        validate(fileIncrement, rankIncrement);
        this.fileIncrement = fileIncrement;
        this.rankIncrement = rankIncrement;
    }

    public boolean canMovedByPawn() {
        return this.rankIncrement == 1 && this.fileIncrement == 0;
    }

    private void validate(int fileIncrement, int rankIncrement) {
        if (fileIncrement == STOP && rankIncrement == STOP) {
            throw new IllegalArgumentException("잘못된 움직임입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return fileIncrement == movement.fileIncrement && rankIncrement == movement.rankIncrement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileIncrement, rankIncrement);

    }
}