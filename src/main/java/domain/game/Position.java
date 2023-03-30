package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Position {
    private static final List<Position> cache = generatePositionCache();

    private final Rank rank;
    private final File file;

    private static List<Position> generatePositionCache() {
        List<Position> positions = new ArrayList<>();
        for (File file : File.values()) {
            addPositionOfAllRankByFile(positions, file);
        }
        return positions;
    }

    private static void addPositionOfAllRankByFile(List<Position> positions, File file) {
        for (Rank rank : Rank.values()) {
            positions.add(new Position(file, rank));
        }
    }

    public static Position of(File file, Rank rank) {
        return cache.stream()
                .filter(position -> position.file.equals(file) && position.rank.equals(rank))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalStateException("서버 내부 에러 - File, Rank에 해당하는 Position을 찾을 수 없습니다."));
    }

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Movement calculateMovement(Position targetPosition) {
        int fileIncrement = this.file.calculateIncrement(targetPosition.file);
        int rankIncrement = this.rank.calculateIncrement(targetPosition.rank);
        return new Movement(fileIncrement, rankIncrement);
    }

    public boolean hasRankOf(Rank rank) {
        return this.rank == rank;
    }

    public boolean hasFileOf(File file) {
        return this.file == file;
    }

    public List<Position> getPath(Position targetPosition) {
        Position movingPosition = Position.of(this.file, this.rank);
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
        return Position.of(file, rank);
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

    public File getFile() {
        return this.file;
    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
