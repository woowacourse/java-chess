package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

import chess.domain.Team;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;

public enum PieceFactory {
	KING("k", King::new),
	BISHOP("b", Bishop::new),
	BLANK(".", (team, position) -> new Blank(position)),
	KNIGHT("n", Knight::new),
	PAWN("p", Pawn::new),
	QUEEN("q", Queen::new),
	ROOK("r", Rook::new);

	private final String name;
	private final BiFunction<Team, Position, Piece> expression;

	PieceFactory(String name, BiFunction<Team, Position, Piece> expression) {
		this.name = name;
		this.expression = expression;
	}

	public static Piece of(String name, String team, String position) {
		PieceFactory pieveFactory = Arrays.stream(values())
			.filter(value -> value.name.equals(name))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("없는 말입니다."));
		Team color = Team.of(team);
		return pieveFactory.expression.apply(color, Position.of(position));
	}
}
