package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {
	PAWN("p", Pawn::new),
	ROOK("r", Rook::new),
	KNIGHT("n", Knight::new),
	BISHOP("b", Bishop::new),
	QUEEN("q", Queen::new),
	KING("k", King::new);

	private final String symbol;
	private final BiFunction<Position, Color, Piece> creator;

	PieceFactory(String symbol, BiFunction<Position, Color, Piece> creator) {
		this.symbol = symbol;
		this.creator = creator;
	}

	public static Piece create(String symbol, Position position, Color color) {
		return Arrays.stream(values())
				.filter(pieceFactory -> pieceFactory.symbol.equals(symbol))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new)
				.creator.apply(position, color);
	}
}
