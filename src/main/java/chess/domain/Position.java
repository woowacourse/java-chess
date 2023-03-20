package chess.domain;

import java.util.Objects;

public final class Position {

    private final int rank;
    private final char file;

     private Position(final int rank, final char file) {
        validatePosition(rank, file);
        this.rank = rank;
        this.file = file;
    }

    public static Position from(final int rank, final char file) {
        return new Position(rank, file);
    }

    private void validatePosition(final int rank, final char file) {
        validateRankRange(rank);
        validateFileRange(file);
    }
    
    private void validateRankRange(final int rank) {
        if (isPieceFileOutOfRange(rank)) {
            throw new IllegalArgumentException("기물의 가로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
        }
    }

    private void validateFileRange(final char file) {
        if (isPieceRankOutOfRange(file)) {
            throw new IllegalArgumentException("기물의 세로 위치는 a부터 h까지 놓을 수 있습니다.");
        }
    }

    private static boolean isPieceFileOutOfRange(int piecePosition) {
        return piecePosition < 0 || 7 < piecePosition;
    }

    private boolean isPieceRankOutOfRange(int rank) {
        return rank < 97 || 104 < rank;
    }

    public Position changePosition(int rank) {
        return new Position(rank, this.file);
    }

    public int getRank() {
        return this.rank;
    }

    public char getFile() {
        return this.file;
    }

    public boolean isSame(char file, int rank) {
        return this.file == file && this.rank == rank;
    }

    public boolean isSamePosition(Position findPosition) {
        return isSame(findPosition.file, findPosition.rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(rank, position.rank) && Objects.equals(file, position.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public int calculateFileDistance(int file) {
        return this.file.calculateDistance(file);
    }

    public int calculateRankDistance(int rank) {
        return this.rank.calculateDistance(rank);
    }

    public Position move(int fileDirection, int rankDirection) {
        return new Position(rank + rankDirection, (char)(file + fileDirection));
    }

}
