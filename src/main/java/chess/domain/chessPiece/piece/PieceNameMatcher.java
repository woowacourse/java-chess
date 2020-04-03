package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceNameMatcher {
	BLACK_PAWN("p", (String position) -> {
		return new Pawn(Position.of(position), new BlackTeam());
	}), BLACK_ROOK("r", (String position) -> {
		return new Rook(Position.of(position), new BlackTeam());
	}), BLACK_KNIGHT("n", (String position) -> {
		return new Knight(Position.of(position), new BlackTeam());
	}), BLACK_BISHOP("b", (String position) -> {
		return new Bishop(Position.of(position), new BlackTeam());
	}), BLACK_QUEEN("q", (String position) -> {
		return new Queen(Position.of(position), new BlackTeam());
	}), BLACK_KING("k", (String position) -> {
		return new King(Position.of(position), new BlackTeam());
	}), WHITE_PAWN("P", (String position) -> {
		return new Pawn(Position.of(position), new WhiteTeam());
	}), WHITE_ROOK("R", (String position) -> {
		return new Rook(Position.of(position), new WhiteTeam());
	}), WHITE_KNIGHT("N", (String position) -> {
		return new Knight(Position.of(position), new WhiteTeam());
	}), WHITE_BISHOP("B", (String position) -> {
		return new Bishop(Position.of(position), new WhiteTeam());
	}), WHITE_QUEEN("Q", (String position) -> {
		return new Queen(Position.of(position), new WhiteTeam());
	}), WHITE_KING("K", (String position) -> {
		return new King(Position.of(position), new WhiteTeam());
	});

	private final String name;
	private final Function<String, Piece> creator;

	PieceNameMatcher(final String name, final Function<String, Piece> creator) {
		this.name = name;
		this.creator = creator;
	}

	public static Piece create(final String pieceType, final String position) {
		return Arrays.stream(PieceNameMatcher.values())
				.filter(x -> x.name.equals(pieceType))
				.map(x -> x.creator.apply(position))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
