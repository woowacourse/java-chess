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

    public static Square of(final String square) {
        return of(File.of(square.charAt(0)), Rank.of(square.charAt(1)));
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

    public Square moveUp() {
        return Square.of(file, rank.up());
    }

    public Square moveDown() {
        return Square.of(file, rank.down());
    }

    public Square moveLeft() {
        return Square.of(file.left(), rank);
    }

    public Square moveRight() {
        return Square.of(file.right(), rank);
    }

    public Square moveLeftUp() {
        return Square.of(file.left(), rank.up());
    }

    public Square moveLeftDown() {
        return Square.of(file.left(), rank.down());
    }

    public Square moveRightUp() {
        return Square.of(file.right(), rank.up());
    }

    public Square moveRightDown() {
        return Square.of(file.right(), rank.down());
    }

    public boolean canMoveUp() {
        return rank.canMoveUp();
    }

    public boolean canMoveDown() {
        return rank.canMoveDown();
    }

    public boolean canMoveLeft() {
        return file.canMoveLeft();
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
