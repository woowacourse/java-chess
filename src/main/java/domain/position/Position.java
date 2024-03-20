package domain.position;

import domain.Direction;

import java.util.Objects;

public class Position {
    // TODO: 8x8 사이즈 포지션 캐싱하기

    private final ChessFile file;
    private final ChessRank rank;

    public Position(ChessFile file, ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String position) {
        this(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        );
    }

    // TODO : null 제거 및 리팩터링
    public Direction findDirectionTo(Position target) {
        if (file.value() == target.file.value() && rank.index() < target.rank.index()) {
            return Direction.TOP;
        }
        if (file.index() == target.file.index() && rank.index() > target.rank.index()) {
            return Direction.DOWN;
        }
        if (file.index() > target.file.index() && rank.index() == target.rank.index()) {
            return Direction.LEFT;
        }
        if (file.index() < target.file.index() && rank.index() == target.rank.index()) {
            return Direction.RIGHT;
        }
        if (file.index() < target.file.index() && rank.index() < target.rank.index() && Math.abs(target.file.index() - file.index()) == Math.abs(target.rank.index() - rank.index())) {
            return Direction.TOP_RIGHT;
        }
        if (file.index() > target.file.index() && rank.index() < target.rank.index() && Math.abs(target.file.index() - file.index()) == Math.abs(target.rank.index() - rank.index())) {
            return Direction.TOP_LEFT;
        }
        if (file.index() < target.file.index() && rank.index() > target.rank.index() && Math.abs(target.file.index() - file.index()) == Math.abs(target.rank.index() - rank.index())) {
            return Direction.DOWN_RIGHT;
        }
        if (file.index() > target.file.index() && rank.index() > target.rank.index() && Math.abs(target.file.index() - file.index()) == Math.abs(target.rank.index() - rank.index())) {
            return Direction.DOWN_LEFT;
        }
        return null;
    }

    public boolean isRank(ChessRank rank) {
        return this.rank == rank;
    }

    public int calculateDistanceTo(Position target) {
        int fileDistance = Math.abs(target.file.index() - file.index());
        int rankDistance = Math.abs(target.rank.index() - rank.index());

        if (fileDistance > 0) {
            return fileDistance;
        }
        return rankDistance;
    }

    public int indexOfFile() {
        return file.index();
    }

    public int indexOfRank() {
        return rank.index();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
