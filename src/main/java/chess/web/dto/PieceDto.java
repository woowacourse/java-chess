package chess.web.dto;

import java.util.Objects;

public class PieceDto {

    private String name;

    private PieceDto(String name) {
        this.name = name;
    }

    public static PieceDto of(String pieceName) {
        return new PieceDto(pieceName);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceDto)) {
            return false;
        }
        PieceDto that = (PieceDto) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
