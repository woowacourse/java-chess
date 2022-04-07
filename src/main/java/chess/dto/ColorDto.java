package chess.dto;

import chess.domain.Color;

public class ColorDto {
    private final Color value;

    private ColorDto(Color color) {
        this.value = color;
    }

    public static ColorDto from(Color color) {
        return new ColorDto(color);
    }

    public Color getValue() {
        return value;
    }
}