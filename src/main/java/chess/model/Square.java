package chess.model;

import java.util.Objects;

public final class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

//    public boolean canMove(Direction direction, Square targetSquare) {
//        return this.move(direction).equals(targetSquare)
//    }

    public boolean isPawnFirstSquare(Color color) {
        if (color.isBlack()) {
            return rank.equals(Rank.SEVEN);
        }
        return rank.equals(Rank.TWO);
    }
    public Square moveDirection(Direction direction) {
        return new Square(file.add(direction.getRow()), rank.add(direction.getCol()));
    }
}
