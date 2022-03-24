package chess;

import java.util.Objects;

public class Position {
    private static final int ASCII_TO_INT = 96;
    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        //validate()
        this.file = file;
        this.rank = rank;
    }

    public static Position of(char... position){
        File file = File.valueOf(position[0] - ASCII_TO_INT);
        Rank rank = Rank.valueOf(Character.getNumericValue(position[1]));

        return new Position(file, rank);
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
        return position.rank.minus(rank) == direction*distance && file.absMinus(position.file) == 0;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }


    public boolean isBiggerFileThan(Position position){
        return this.file.isBiggerThan(position.file);
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

    public boolean isLessRankThan(Position position) {
        return this.rank.isLessThan(position.rank);
    }
}
