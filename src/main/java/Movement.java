import java.util.Objects;

public class Movement {
    private final int fileIncrement;
    private final int rankIncrement;

    public Movement(int fileIncrement, int rankIncrement) {
        this.fileIncrement = fileIncrement;
        this.rankIncrement = rankIncrement;
    }

    public boolean canMovedByPawn() {
        return this.rankIncrement == 1 && this.fileIncrement == 0;
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