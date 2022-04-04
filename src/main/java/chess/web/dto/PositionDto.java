package chess.web.dto;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PositionDto {
    private final Rank rank;
    private final File file;

    public PositionDto(Position position) {
        this.rank = position.getRank();
        this.file = position.getFile();
    }

    public PositionDto(int rank, String file) {
        this.rank = Rank.toRank(rank);
        this.file = File.toFile(file.charAt(0));
    }

    public int getRank() {
        return rank.getRank();
    }

    public String getFile() {
        return String.valueOf(file.getFile());
    }

    public String getPosition() {
        return String.valueOf(file.getFile())+rank.getRank();
    }

    @Override
    public String toString() {
        return "PositionDto{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }
}
