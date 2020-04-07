package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import chess.domain.Position;

public enum PieceRule {
	KING(0, Arrays.asList('♔', '♚'), (position, team) -> new King(new Position(position), Team.of(team))),
	QUEEN(9, Arrays.asList('♕', '♛'), (position, team) -> new Queen(new Position(position), Team.of(team))),
	ROOK(5, Arrays.asList('♖', '♜'), (position, team) -> new Rook(new Position(position), Team.of(team))),
	BISHOP(3, Arrays.asList('♗', '♝'), (position, team) -> new Bishop(new Position(position), Team.of(team))),
	KNIGHT(2.5, Arrays.asList('♘', '♞'), (position, team) -> new Knight(new Position(position), Team.of(team))),
	PAWN(1, Arrays.asList('♙', '♟'), (position, team) -> new Pawn(new Position(position), Team.of(team)));

	private double score;
	private List<Character> representations;
	private BiFunction<String, String, Piece> generator;

	PieceRule(double score, List<Character> representations, BiFunction<String, String, Piece> generator) {
		this.score = score;
		this.representations = representations;
		this.generator = generator;
	}

	public static PieceRule of(char representation) {
		return Arrays.stream(PieceRule.values())
			.filter(a -> a.representations.contains(representation))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("일치하는 말이 없습니다."));
	}

	public static Piece makeNewPiece(char representation, String position, String team) {
		return PieceRule.of(representation).generator.apply(position, team);
	}

	public double getScore() {
		return score;
	}
}
