package chess.domain.dto;

import chess.domain.board.Position;

import java.util.Objects;

public class PositionDto {
    private String file;
    private String rank;

    public PositionDto(Position position) {
        this.file = position.getFile();
        this.rank = position.getRank();
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return file + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionDto that = (PositionDto) o;

        if (!Objects.equals(file, that.file)) return false;
        return Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
