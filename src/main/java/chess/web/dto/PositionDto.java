package chess.web.dto;

import chess.domain.position.Position;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PositionDto that = (PositionDto) o;
        return rank.equals(that.rank) && file.equals(that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
