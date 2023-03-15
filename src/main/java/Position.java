import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Position {
    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String fileText, String rankText) {
        File file = File.from(fileText);
        Rank rank = Rank.from(rankText);
        return new Position(file, rank);
    }

    public Movement calculateMovement(Position targetPosition) {
        int fileIncrement = this.file.calculateIncrement(targetPosition.file);
        int rankIncrement = this.rank.calculateIncrement(targetPosition.rank);
        return new Movement(fileIncrement, rankIncrement);
    }

    public boolean hasRankOf(Rank defaultRank) {
        return rank == defaultRank;
    }

    public List<Position> getPath(Position targetPosition) {
        Position movingPosition = new Position(this.file, this.rank);
        Movement movement = this.calculateMovement(targetPosition);

        List<Position> path = new ArrayList<>();
        while (!movement.isOneStep()) {
            movingPosition = movingPosition.moveOneStepBy(movement);
            movement = movingPosition.calculateMovement(targetPosition);
            path.add(movingPosition);
        }
        return path;
    }

    private Position moveOneStepBy(Movement direction) {
        File file = getDirectionOfFile(direction);
        Rank rank = getDirectionOfRank(direction);
        return new Position(file, rank);
    }

    private File getDirectionOfFile(Movement direction) {
        if (direction.isRight()) {
            return this.file.getNext();
        }
        if (direction.isLeft()) {
            return this.file.getPrevious();
        }
        return this.file;
    }

    private Rank getDirectionOfRank(Movement direction) {
        if (direction.isUpward()) {
            return this.rank.getNext();
        }
        if (direction.isDownward()) {
            return this.rank.getPrevious();
        }
        return this.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
