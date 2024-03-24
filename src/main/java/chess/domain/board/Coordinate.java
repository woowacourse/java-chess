package chess.domain.board;

import java.util.Objects;
import chess.domain.piece.Weight;

public class Coordinate {

    private final Rank rank;
    private final File file;

    public Coordinate(int rankValue, char fileValue) {
        this.rank = new Rank(rankValue);
        this.file = new File(fileValue);
    }

    public Coordinate apply(Weight weight) {
        if (!isApplicable(weight)) {
            throw new IllegalStateException("해당 가중치를 적용할 수 없습니다.");
        }

        char fileWeight = weight.fileWeight();
        int rankWeight = weight.rankWeight();
        return new Coordinate(rank.getValue() + rankWeight, (char) (file.getValue() + fileWeight));
    }

    public boolean isApplicable(Weight weight) {
        int rankWeight = weight.rankWeight();
        char fileWeight = weight.fileWeight();

        //TODO: rank, file의 applicable을 생성하도록 개선 가능할 듯!
        try {
            new Coordinate(rank.getValue() + rankWeight, (char) (file.getValue() + fileWeight));
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public int getRank() {
        return rank.getValue();
    }

    public char getFile() {
        return file.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(rank, that.rank) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }
}
