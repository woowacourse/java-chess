package chess.domain.position;

import chess.domain.piece.Color;
import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean findPosition(Rank rank, File file) {
        return rank == this.rank && file == this.file;
    }

    public int calculateRankDifference(Position target) {
        return rank.calculateDifference(target.rank);
    }

    public int calculateFileDifference(Position target) {
        return file.calculateDifference(target.file);
    }

    public Position move(int rankUnit, int fileUnit) {
        Rank movedRank = rank.move(rankUnit);
        File movedFile = file.move(fileUnit);

        return Positions.of(movedRank, movedFile);
    }

    public boolean isPawnFirstTry(Color color) {
        if (color == Color.BLACK && rank == Rank.SEVEN) {
            return true;
        }
        return color == Color.WHITE && rank == Rank.TWO;
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
}
