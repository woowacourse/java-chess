package chess.domain.board;

import chess.domain.player.TeamType;
import java.util.Objects;

public class Coordinate {

    private final File file;
    private final Rank rank;

    public Coordinate(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Coordinate from(String currentCoordinateInput) {
        String[] currentSplit = currentCoordinateInput.split("");
        String fileInput = currentSplit[0];
        String rankInput = currentSplit[1];
        File file = File.findValueOf(fileInput);
        Rank rank = Rank.findValueOf(rankInput);
        return new Coordinate(file, rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }


    public Coordinate moveForward(TeamType teamType) {
        if (teamType == TeamType.BLACK) {
            return new Coordinate(this.file, this.rank.decrease());
       }
        return null;
    }

    public Direction calculateDirection(Coordinate targetCoordinate) {
        File targetFile = targetCoordinate.getFile();
        Rank targetRank = targetCoordinate.getRank();

        int fileDiff = targetFile.getX() - file.getX();
        int rankDiff = targetRank.getY() - rank.getY();

        return Direction.findDirection(fileDiff, rankDiff);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Coordinate move(Direction direction) {
        return new Coordinate(file.move(direction), rank.move(direction));
    }

    public boolean isTwoRankForward(Coordinate targetCoordinate) {
        return Math.abs(targetCoordinate.getRank().getY()
            - this.rank.getY()) == 2 && file.getX() == targetCoordinate.getFile().getX();
    }

    public boolean isFirstPawnRank(TeamType teamType) {
        if (teamType == TeamType.BLACK) {
            return rank == Rank.SEVEN;
        }
        return rank == Rank.TWO;
    }
}
