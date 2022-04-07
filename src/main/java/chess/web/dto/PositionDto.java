package chess.web.dto;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PositionDto {
    private final int rank;
    private final char file;

    public PositionDto(int rank, char file) {
        this.rank = rank;
        this.file = file;
    }

    public static PositionDto from(Position position) {
        Rank rank = position.getRank();
        File file = position.getFile();

        return new PositionDto(rank.getRank(), file.getFile());
    }

    public int getRank() {
        return rank;
    }

    public String getFile() {
        return String.valueOf(file);
    }

    public String getPosition() {
        return String.valueOf(file)+rank;
    }
}
