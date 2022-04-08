package chess.board.piece.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {
    private static final int ASCII_TO_INT = 96;
    private static final int CHESS_POSITION_SIZE = 64;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final Map<String, Position> CACHE = new HashMap<>(CHESS_POSITION_SIZE);

    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(char... position) {
        File file = File.valueOf(position[FILE_INDEX] - ASCII_TO_INT);
        Rank rank = Rank.valueOf(Character.getNumericValue(position[RANK_INDEX]));
        return CACHE.computeIfAbsent(createKey(file, rank), absent -> new Position(file, rank));
    }

    public static Position from(String position) {
        File file = File.valueOf(position.charAt(FILE_INDEX) - ASCII_TO_INT);
        Rank rank = Rank.valueOf(Character.getNumericValue(position.charAt(RANK_INDEX)));
        return CACHE.computeIfAbsent(createKey(file, rank), absent -> new Position(file, rank));
    }

    public String name() {
        return file.name().toLowerCase() + rank.getIndex();
    }

    private static String createKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public boolean isHorizontal(Position position) {
        return this.rank == position.rank;
    }

    public boolean isVertical(Position position) {
        return this.file == position.file;
    }

    public boolean isDiagonal(Position position) {
        return rank.absMinus(position.rank) == file.absMinus(position.file);
    }

    public boolean isPositiveDiagonal(Position position) {
        return this.rank.minus(position.rank) == this.file.minus(position.file);
    }

    public boolean isNegativeDiagonal(Position position) {
        return this.rank.minus(position.rank) == position.file.minus(this.file);
    }

    public boolean isOneStepAway(Position position) {
        return isOneStepHorizontalOrVertical(position) || isOneStepDiagonal(position);
    }

    public boolean isKnightDirection(Position position) {
        return isTwoFileOneRankStep(position) || isOneFileTwoRankStep(position);
    }

    private boolean isOneFileTwoRankStep(Position position) {
        return rank.absMinus(position.rank) == 2 && file.absMinus(position.file) == 1;
    }

    private boolean isTwoFileOneRankStep(Position position) {
        return file.absMinus(position.file) == 2 && rank.absMinus(position.rank) == 1;
    }

    private boolean isOneStepHorizontalOrVertical(Position position) {
        return rank.absMinus(position.rank) + file.absMinus(position.file) == 1;
    }

    private boolean isOneStepDiagonal(Position position) {
        return rank.absMinus(position.rank) == 1 && file.absMinus(position.file) == 1;
    }

    public boolean isInitPawnPosition(Rank rank) {
        return this.rank.equals(rank);
    }

    public boolean isStepForward(Position position, int direction, int distance) {
        return position.rank.minus(rank) == direction * distance && file.absMinus(position.file) == 0;
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }

    public boolean isBiggerFileThan(Position position) {
        return this.file.isBiggerThan(position.file);
    }

    public boolean isLessRankThan(Position position) {
        return this.rank.isLessThan(position.rank);
    }

    public boolean isLastFile() {
        return file.equals(File.H);
    }

    public boolean isTwoStepAway(Position position) {
        return rank.absMinus(position.rank) == 2;
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


    public Position getRightHorizontalPosition(int distance) {
        return new Position(file.getNext(distance), rank);
    }

    public Position getUpVerticalPosition(int distance) {
        return new Position(file, rank.getNext(distance));
    }

    public Position getPositiveDiagonalPosition(int distance) {
        return new Position(file.getNext(distance), rank.getNext(distance));
    }

    public Position getNegativeDiagonalPosition(int distance) {
        return new Position(file.getNext(distance), rank.getNext(-distance));
    }

    public boolean isOneStepDiagonal(Position position, int forwardDirection) {
        return position.rank.minus(rank) == forwardDirection && file.absMinus(position.file) == 1;
    }
}
