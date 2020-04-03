package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessPiece.team.WhiteTeam;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceCreator {
	PAWN("p", (String position, TeamStrategy team) -> {
		return new Pawn(Position.of(position), team);
	}), ROOK("r", (String position, TeamStrategy team) -> {
		return new Rook(Position.of(position), team);
	}), KNIGHT("n", (String position, TeamStrategy team) -> {
		return new Knight(Position.of(position), team);
	}), BISHOP("b", (String position, TeamStrategy team) -> {
		return new Bishop(Position.of(position), team);
	}), QUEEN("q", (String position, TeamStrategy team) -> {
		return new Queen(Position.of(position), team);
	}), KING("k", (String position, TeamStrategy team) -> {
		return new King(Position.of(position), team);
	});

	private final String name;
	private final BiFunction<String, TeamStrategy, Piece> creator;

	PieceCreator(final String name, final BiFunction<String, TeamStrategy, Piece> creator) {
		this.name = name;
		this.creator = creator;
	}

	public static Piece create(final String pieceType, final String position) {
		TeamStrategy team = findTeam(pieceType);
		return Arrays.stream(PieceCreator.values())
				.filter(x -> x.name.equalsIgnoreCase(pieceType))
				.map(x -> x.creator.apply(position, team))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	private static TeamStrategy findTeam(final String pieceType) {
		if (Character.isUpperCase(pieceType.charAt(0))) {
			return new WhiteTeam();
		}
		return new BlackTeam();
	}
}
