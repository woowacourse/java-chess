package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> cachePosition = new HashMap<>(64);

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int VALID_SIZE = 2;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position valueOf(String input) {
        validateBlank(input);
        validateSize(input);

        File file = File.of(input.substring(FILE_INDEX, FILE_INDEX + 1));
        Rank rank = Rank.of(input.substring(RANK_INDEX, RANK_INDEX + 1));

        if (!cachePosition.containsKey(input)) {
            cachePosition.put(input, new Position(file, rank));
        }
        return cachePosition.get(input);
    }

    public static Position withFileAndRank(File file, Rank rank) {
        String key = file.getValue() + rank.getValue();
        return valueOf(key);
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("포지션은 빈값일 수 없습니다.");
        }
    }

    private static void validateSize(String input) {
        if (input.length() != VALID_SIZE) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    public Position move(int x, int y) {
        return new Position(file.move(x), rank.move(y));
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(Position position) {
        return this.file.equals(position.file);
    }

    public boolean canCrossMovingStraight(Direction direction, Position destination) {
        return moveToNextPositionCheckingDestination(this, destination, direction.getX(), direction.getY());
    }

    private boolean moveToNextPositionCheckingDestination(Position start, Position destination, int x, int y) {
        if (!start.canMove(x, y)) {
            return false;
        }
        Position nextPosition = start.move(x, y);
        if (nextPosition.equals(destination)) {
            return true;
        }
        return moveToNextPositionCheckingDestination(nextPosition, destination, x, y);
    }

    public boolean canMoveByTime(Direction direction, Position destination, int time) {
        int x = direction.getX();
        int y = direction.getY();

        Position nextPosition = this;
        for (int i = 0; i < time; i++) {
            if (!nextPosition.canMove(x, y)) {
                return false;
            }
            nextPosition = nextPosition.move(x, y);
        }
        return destination.equals(nextPosition);
    }

    private boolean canMove(int x, int y) {
        return file.canMove(x) && rank.canMove(y);
    }

    public String getStringValue() {
        return file.getValue() + rank.getValue();
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                file +
                ", " + rank +
                '}';
    }
}
