package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {

	private final String name;
	private final Color color;

	protected Piece(final String name, final Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Piece)) {
			return false;
		}
		Piece piece = (Piece)o;
		return Objects.equals(name, piece.name) && Objects.equals(color, piece.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
