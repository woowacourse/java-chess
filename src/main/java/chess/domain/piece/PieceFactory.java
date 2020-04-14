package chess.domain.piece;

import static chess.domain.piece.Team.*;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceFactory {
	BLACK_PAWN("P", () -> new Pawn(BLACK)),
	BLACK_BISHOP("B", () -> new Bishop(BLACK)),
	BLACK_KNIGHT("N", () -> new Knight(BLACK)),
	BLACK_ROOK("R", () -> new Rook(BLACK)),
	BLACK_QUEEN("Q", () -> new Queen(BLACK)),
	BLACK_KING("K", () -> new King(BLACK)),
	WHITE_PAWN("p", () -> new Pawn(WHITE)),
	WHITE_BISHOP("b", () -> new Bishop(WHITE)),
	WHITE_KNIGHT("n", () -> new Knight(WHITE)),
	WHITE_ROOK("r", () -> new Rook(WHITE)),
	WHITE_QUEEN("q", () -> new Queen(WHITE)),
	WHITE_KING("k", () -> new King(WHITE));

	private final String symbol;
	private final Supplier<Piece> pieceSupplier;

	PieceFactory(String symbol, Supplier<Piece> pieceSupplier) {
		this.symbol = symbol;
		this.pieceSupplier = pieceSupplier;
	}

	public static Piece of(String symbol) {
		return Arrays.stream(values())
			.filter(val -> val.symbol.equals(symbol))
			.map(val -> val.pieceSupplier.get())
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효한 심볼이 아닙니다. : " + symbol));
	}
}
