package chess.domain.board;

import chess.domain.piece.Pawn;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    static {
        for (final File file : File.values()) {
            makePositionWith(file);
        }
    }

    private static void makePositionWith(File file) {
        for (final Rank rank : Rank.values()) {
            POSITIONS.put(makeKey(file, rank), new Position(file, rank));
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String position) {
        return POSITIONS.get(position);
    }

    public static Position of(File file, Rank rank) {
        return POSITIONS.get(makeKey(file, rank));
    }

    private static String makeKey(File file, Rank rank) {
        return file.getFile() + rank.getRank();
    }

    public Direction calculateDirection(Position target) {
        int x = calculateFilePoint(target.getX());
        int y = calculateRankPoint(target.getY());
        int divide = Math.abs(gcd(x, y));
        return Direction.of(x / divide, y / divide);
    }

    private int calculateFilePoint(int x) {
        return x - this.getX();
    }

    private int calculateRankPoint(int y) {
        return y - this.getY();
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int getX() {
        return file.getIndex();
    }

    public int getY() {
        return rank.getRank();
    }

    public Position move(Direction dir, int step) {
        return Position.of(File.of(this.file.getIndex() + dir.getXDegree() * step),
            Rank.of(this.rank.getRank() + dir.getYDegree() * step));
    }

    public int calculateDistance(Position source) {
        return (int) Math.sqrt(
            (Math.pow(this.getX() - source.getX(), 2) + Math.pow(this.getY() - source.getY(), 2)));
    }

    public boolean isDiagonal(Position position) {
        return Math.abs(calculateFileGap(position)) == Math.abs(calculateRankGap(position));
    }

    private int calculateRankGap(Position position) {
        return this.getY() - position.getY();
    }

    private int calculateFileGap(Position position) {
        return this.getX() - position.getX();
    }

    public boolean isLocatedAtStartLine() {
        return this.getY() == Pawn.WHITE_PAWN_START_LINE
            || this.getY() == Pawn.BLACK_PAWN_START_LINE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
