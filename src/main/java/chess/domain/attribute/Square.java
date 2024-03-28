package chess.domain.attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Square {

    private static final Map<String, Square> SQUARES = new HashMap<>();

    static {
        initializeSquares();
    }

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final File file, final Rank rank) {
        return SQUARES.get(keyOf(file, rank));
    }

    private static void initializeSquares() {
        for (final Rank rank : Rank.values()) {
            putSquares(rank);
        }
    }

    private static void putSquares(final Rank rank) {
        for (final File file : File.values()) {
            SQUARES.put(keyOf(file, rank), new Square(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    public boolean canMove(Movement movement) {
        return canMoveHorizontal(movement.getX()) && canMoveVertical(movement.getY());
    }

    public boolean canMoveVertical(int step) {
        if (step > 0) {
            return canMoveUp(Math.abs(step));
        }
        if (step < 0) {
            return canMoveDown(Math.abs(step));
        }
        return true;
    }

    public boolean canMoveHorizontal(int step) {
        if (step > 0) {
            return canMoveRight(Math.abs(step));
        }
        if (step < 0) {
            return canMoveLeft(Math.abs(step));
        }
        return true;
    }

    public Square move(Movement movement) {
        return moveHorizontal(movement.getX()).moveVertical(movement.getY());
    }

    public Square moveHorizontal(int step) {
        if (step < 0) {
            return moveLeft(Math.abs(step));
        }
        if (step > 0) {
            return moveRight(Math.abs(step));
        }
        return this;
    }

    public Square moveVertical(int step) {
        if (step < 0) {
            return moveDown(Math.abs(step));
        }
        if (step > 0) {
            return moveUp(Math.abs(step));
        }
        return this;
    }

    public Square moveUp(int step) {
        return Square.of(file, rank.up(Math.abs(step)));
    }

    public Square moveUp() {
        return Square.of(file, rank.up());
    }

    public Square moveDown(int step) {
        return Square.of(file, rank.down(Math.abs(step)));
    }

    public Square moveDown() {
        return Square.of(file, rank.down());
    }

    public Square moveLeft(int step) {
        return Square.of(file.left(Math.abs(step)), rank);
    }

    public Square moveLeft() {
        return Square.of(file.left(), rank);
    }

    public Square moveRight(int step) {
        return Square.of(file.right(Math.abs(step)), rank);
    }

    public Square moveRight() {
        return Square.of(file.right(), rank);
    }

    public Square moveLeftUp(int step) {
        return Square.of(file.left(Math.abs(step)), rank.up());
    }

    public Square moveLeftUp() {
        return Square.of(file.left(), rank.up());
    }

    public Square moveLeftDown(int step) {
        return Square.of(file.left(Math.abs(step)), rank.down());
    }

    public Square moveLeftDown() {
        return Square.of(file.left(), rank.down());
    }

    public Square moveRightUp(int step) {
        return Square.of(file.right(Math.abs(step)), rank.up());
    }

    public Square moveRightUp() {
        return Square.of(file.right(), rank.up());
    }

    public Square moveRightDown(int step) {
        return Square.of(file.right(Math.abs(step)), rank.down());
    }

    public Square moveRightDown() {
        return Square.of(file.right(), rank.down());
    }

    public boolean canMoveUp(int step) {
        return rank.canMoveUp(Math.abs(step));
    }

    public boolean canMoveUp() {
        return rank.canMoveUp();
    }

    public boolean canMoveDown(int step) {
        return rank.canMoveDown(Math.abs(step));
    }

    public boolean canMoveDown() {
        return rank.canMoveDown();
    }

    public boolean canMoveLeft(int step) {
        return file.canMoveLeft(Math.abs(step));
    }

    public boolean canMoveLeft() {
        return file.canMoveLeft();
    }

    public boolean canMoveRight(int step) {
        return file.canMoveRight(Math.abs(step));
    }

    public boolean canMoveRight() {
        return file.canMoveRight();
    }

    public boolean canMoveLeftUp() {
        return file.canMoveLeft() && rank.canMoveUp();
    }

    public boolean canMoveLeftDown() {
        return file.canMoveLeft() && rank.canMoveDown();
    }

    public boolean canMoveRightUp() {
        return file.canMoveRight() && rank.canMoveUp();
    }

    public boolean canMoveRightDown() {
        return file.canMoveRight() && rank.canMoveDown();
    }

    public boolean isStartRankOfBlackPawn() {
        return rank.isRankSeven();
    }

    public boolean isStartRankOfWhitePawn() {
        return rank.isRankTwo();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Square other
                && rank == other.rank
                && file == other.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
