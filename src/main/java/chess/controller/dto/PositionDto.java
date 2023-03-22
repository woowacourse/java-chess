package chess.controller.dto;

import java.util.Objects;

public final class PositionDto {

    private final int rank;
    private final int file;

    public PositionDto(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public static PositionDto from(final int rank, final int file) {
        return new PositionDto(rank, file);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PositionDto that = (PositionDto) o;
        return rank == that.rank && file == that.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
