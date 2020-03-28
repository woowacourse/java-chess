package chess.domain.movepattern;

import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MovePatternFactory {
	private static final List<MovePattern> movePatterns = new ArrayList<>();

	static {
		movePatterns.add(new StraightPattern(Position.of("a1"), Position.of("a1")));
		movePatterns.add(new CrossPattern(Position.of("a1"), Position.of("a1")));
		movePatterns.add(new KnightPattern());
	}

	public static MovePattern findMovePattern(Position source, Position target) {
		return movePatterns.stream()
				.filter(x -> x.isMatchedPoints(source, target))
				.map(x -> x.valueOf(source, target))
				.findFirst()
				.orElseThrow(() -> {
					throw new IllegalArgumentException(Piece.ERROR_MESSAGE_NOT_MOVABLE);
				});
	}
}
