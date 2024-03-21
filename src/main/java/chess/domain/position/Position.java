package chess.domain.position;

import chess.domain.piece.Color;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean findPosition(File file, Rank rank) {
        return file == this.file && rank == this.rank;
    }

    public int calculateFileDifference(Position target) {
        return file.calculateDifference(target.file);
    }

    public int calculateRankDifference(Position target) {
        return rank.calculateDifference(target.rank);
    }

    public Position move(int fileUnit, int rankUnit) {
        File movedFile = file.move(fileUnit);
        Rank movedRank = rank.move(rankUnit);

        return Positions.of(movedFile, movedRank);
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
