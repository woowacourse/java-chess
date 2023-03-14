import java.util.Objects;

public class Increment {
    private final int fileIncrement;
    private final int rankIncrement;

    public Increment(int fileIncrement, int rankIncrement) {
        this.fileIncrement = fileIncrement;
        this.rankIncrement = rankIncrement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Increment increment = (Increment) o;
        return fileIncrement == increment.fileIncrement && rankIncrement == increment.rankIncrement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileIncrement, rankIncrement);
    }
}