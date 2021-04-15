package chess.domain.board;

import chess.domain.piece.Pawn;

import java.util.*;

public class Position {
    private static final Map<String, Position> POSITIONS = new LinkedHashMap<>();

    static {
        for (final Rank rank : Rank.values()) {
            makePositionWith(rank);
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static void makePositionWith(Rank rank) {
        for (final File file : File.values()) {
            POSITIONS.put(makeKey(file, rank), new Position(file, rank));
        }
    }

    public static List<Position> getAllPositions() {
        return new ArrayList<>(POSITIONS.values());
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

    public int calculateFilePoint(Position target) {
        return target.getX() - this.getX();
    }

    public int calculateRankPoint(Position target) {
        return target.getY() - this.getY();
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

    public double powAxisX(Position x) {
        return Math.pow(this.getX() - x.getX(), 2);
    }

    public double powAxisY(Position y) {
        return Math.pow(this.getY() - y.getY(), 2);
    }

    public int calculateRankGap(Position position) {
        return this.getY() - position.getY();
    }

    public int calculateFileGap(Position position) {
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

    @Override
    public String toString() {
        return file.getFile() + rank.getRank();
    }

    public String getFile() {
        return file.getFile();
    }

    public String getRank() {
        return String.valueOf(rank.getRank());
    }
}
