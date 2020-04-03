package chess.domain.dto;

import chess.domain.piece.Color;

public class TurnDto {
	private Color color;

	private TurnDto(Color color) {
		this.color = color;
	}

	public static TurnDto of(String color) {
		return new TurnDto(Color.of(color));
	}

	public static TurnDto of(Color color) {
		return new TurnDto(color);
	}

	public Color getColor() {
		return color;
	}

	public String getColorName() {
		return color.name();
	}
}
