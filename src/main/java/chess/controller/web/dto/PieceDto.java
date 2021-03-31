package chess.controller.web.dto;

import java.util.Objects;

public class PieceDto {
    private final String type;
    private final String color;

    public PieceDto(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceDto)) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(getType(), pieceDto.getType()) && Objects.equals(getColor(), pieceDto.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getColor());
    }
}
