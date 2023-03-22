package chess.domain.dto;

import chess.domain.Position;

import java.util.Objects;

public class PositionDto {

    private final int rank;
    private final char file;

    public PositionDto(Position position) {
        this.rank = position.getRankValue();
        this.file = position.getFileValue();
    }

    public int getRank() {
        return rank;
    }

    public char getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDto that = (PositionDto) o;
        return rank == that.rank && file == that.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

}
