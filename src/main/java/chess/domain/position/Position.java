package chess.domain.position;

import java.util.Objects;

public class Position {
    private Rank rank;
    private File file;

    public Position(String text){
        this.rank = Rank.of(String.valueOf(text.charAt(0)));
        this.file = File.of(String.valueOf(text.charAt(1)));
    }

    public int fileDistance(Position target){
        return Math.abs(file.calculateDistance(target.file));
    }

    public int rankDistance(Position target){
        return Math.abs(rank.calculateDistance(target.rank));
    }

    public boolean isSameFile(Position target) {
        return file.equals(target.file);
    }

    public boolean isSameRank(Position target) {
        return rank.equals(target.rank);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
