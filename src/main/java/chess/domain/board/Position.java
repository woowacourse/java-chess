package chess.domain.board;

import java.util.Objects;

public class Position {

    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final int VALID_SIZE = 2;

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String input) {
        validateBlank(input);
        validateSize(input);

        String[] values = input.split("");
        return new Position(File.of(values[COLUMN_INDEX]), Rank.of(values[ROW_INDEX]));
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

    public boolean isSameRow(Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(Position position) {
        return this.file.equals(position.file);
    }

    public boolean canCrossMovingStraight(Direction direction, Position destination) {
        return moveToNextPositionCheckingdestinationination(this, destination, direction.getX(), direction.getY());
    }

    private boolean moveToNextPositionCheckingdestinationination(Position start, Position destination, int x, int y) {
        if (!start.canMove(x, y)) {
            return false;
        }
        Position nextPosition = start.move(x, y);
        if (nextPosition.equals(destination)) {
            return true;
        }
        return moveToNextPositionCheckingdestinationination(nextPosition, destination, x, y);
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
