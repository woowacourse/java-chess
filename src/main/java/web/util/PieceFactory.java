package web.util;

import java.util.Arrays;
import java.util.function.Function;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.position.Position;
import domain.piece.team.Team;

public enum PieceFactory {
	WHITE_PAWN("p", position -> new Pawn(Position.of(position), Team.WHITE)),
	WHITE_ROOK("r", position -> new Rook(Position.of(position), Team.WHITE)),
	WHITE_KNIGHT("n", position -> new Knight(Position.of(position), Team.WHITE)),
	WHITE_BISHOP("b", position -> new Bishop(Position.of(position), Team.WHITE)),
	WHITE_QUEEN("q", position -> new Queen(Position.of(position), Team.WHITE)),
	WHITE_KING("k", position -> new King(Position.of(position), Team.WHITE)),

	BLACK_PAWN("P", position -> new Pawn(Position.of(position), Team.BLACK)),
	BLACK_ROOK("R", position -> new Rook(Position.of(position), Team.BLACK)),
	BLACK_KNIGHT("N", position -> new Knight(Position.of(position), Team.BLACK)),
	BLACK_BISHOP("B", position -> new Bishop(Position.of(position), Team.BLACK)),
	BLACK_QUEEN("Q", position -> new Queen(Position.of(position), Team.BLACK)),
	BLACK_KING("K", position -> new King(Position.of(position), Team.BLACK));

	private String symbol;
	private Function<String, Piece> generate;

	PieceFactory(String symbol, Function<String, Piece> generate) {
		this.symbol = symbol;
		this.generate = generate;
	}

	public static PieceFactory of(String symbol) {
		return Arrays.stream(values())
			.filter(piece -> piece.symbol.equals(symbol))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당 말이 존재하지 않습니다."));
	}

	public Function<String, Piece> getGenerate() {
		return generate;
	}
}
