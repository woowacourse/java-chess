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
}
