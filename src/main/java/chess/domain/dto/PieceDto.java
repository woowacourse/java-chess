package chess.domain.dto;

import java.util.Objects;

public class PieceDto {
    private final String name;
    private final String color;

    public PieceDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(name, pieceDto.name) && Objects.equals(color, pieceDto.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    public String getColor() {
        return color;
    }
}
