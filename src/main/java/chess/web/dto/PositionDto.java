package chess.web.dto;

import chess.domain.position.Position;

public class PositionDto {

    private final String rank;
    private final String file;

    private PositionDto(String rank, String file) {
        this.rank = rank;
        this.file = file;
    }

    public static PositionDto from(Position position) {
        return new PositionDto(position.getRow().name(), position.getColumn().name());
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return rank + " " + file;
    }
}
