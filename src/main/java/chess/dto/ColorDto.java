package chess.dto;

import chess.domain.Color;

public class ColorDto {

    private final String colorName;

    private ColorDto(final String colorName) {
        this.colorName = colorName;
    }

    public static ColorDto toDto(final Color color) {
        return new ColorDto(color.getName());
    }

    public String getColorName() {
        return colorName;
    }
}
