package chess.model;

import java.util.Objects;

public final class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(String squareName) {
        if (squareName.length() != 2) {
            throw new IllegalArgumentException("잘못된 위치를 입력하였습니다.");
        }
        File file = File.valueOf(String.valueOf(Character.toUpperCase(squareName.charAt(0))));
        Rank rank = Rank.of(Character.getNumericValue(squareName.charAt(1)));
        return new Square(file, rank);
    }

    public boolean isPawnFirstSquare(Color color) {
        if (color.isBlack()) {
            return rank.equals(Rank.SEVEN);
        }
        return rank.equals(Rank.TWO);
    }

    public int getDistance(Square target) {
        Direction direction = this.findDirection(target);
        Square tempSquare = this;
        int movedCount = 0;
        while (!tempSquare.equals(target)) {
            tempSquare = tempSquare.move(direction);
            movedCount ++;
        }
        return movedCount;
    }

    public Square move(Direction direction) {
        return new Square(file.add(direction.getRow()), rank.add(direction.getCol()));
    }

    public Direction findDirection(Square source) {
        int fileDistance = file.calculateGap(source.file);
        int rankDistance = rank.calculateGap(source.rank);
        int gcd = gcd(fileDistance, rankDistance);
        return Direction.of(fileDistance / gcd, rankDistance / gcd);
    }

    private static int gcd(int fileDistance, int rankDistance) {
        fileDistance = Math.abs(fileDistance);
        rankDistance = Math.abs(rankDistance);
        int bigger = Math.max(fileDistance, rankDistance);
        int smaller = Math.min(fileDistance, rankDistance);
        while (smaller > 0) {
            int tmp = bigger;
            bigger = smaller;
            smaller = tmp % smaller;
        }
        return bigger;
    }

    public boolean isSameFile(Square other) {
        return this.file.equals(other.file);
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
}
