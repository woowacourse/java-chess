package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

import chess.domain.position.Position;

public enum PieceFactory {
	WHITE_KING("♔", (position) -> new King(position, Team.WHITE)),
	BLACK_KING("♚", (position) -> new King(position, Team.BLACK)),
	WHITE_QUEEN("♕", (position) -> new Queen(position, Team.WHITE)),
	BLACK_QUEEN("♛", (position) -> new Queen(position, Team.BLACK)),
	WHITE_ROOK("♖", (position) -> new Rook(position, Team.WHITE)),
	BLACK_ROOK("♜", (position) -> new Rook(position, Team.BLACK)),
	WHITE_BISHOP("♗", (position) -> new Bishop(position, Team.WHITE)),
	BLACK_BISHOP("♝", (position) -> new Bishop(position, Team.BLACK)),
	WHITE_KNIGHT("♘", (position) -> new Knight(position, Team.WHITE)),
	BLACK_KNIGHT("♞", (position) -> new Knight(position, Team.BLACK)),
	WHITE_PAWN("♙", (position) -> new Pawn(position, Team.WHITE)),
	BLACK_PAWN("♟", (position) -> new Pawn(position, Team.BLACK)),
	EMPTY(" ", (position) -> new Empty(position, Team.NONE));

	private final String symbol;
	private final Function<Position, Piece> pieceGenerator;

	PieceFactory(String symbol, Function<Position, Piece> pieceGenerator) {
		this.symbol = symbol;
		this.pieceGenerator = pieceGenerator;
	}

	public static PieceFactory of(String symbol) {
		return Arrays.stream(PieceFactory.values())
			.filter(value -> value.symbol.equals(symbol))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 기물 입니다."));
	}

	public Piece create(Position position) {
		return this.pieceGenerator.apply(position);
	}
}
