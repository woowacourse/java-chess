package domain.board;

import chess.domain.board.Position;

public class PositionFixtures {

	public static Position initialWhiteKing = Position.of(1, 5);
	public static Position initialWhiteQueen = Position.of(1, 4);
	public static Position initialWhiteBishop = Position.of(1, 3);
	public static Position initialWhiteKnight = Position.of(1, 2);
	public static Position initialWhiteRook = Position.of(1, 1);

	public static Position initialBlackKing = Position.of(8, 5);
	public static Position initialBlackQueen = Position.of(8, 4);
	public static Position initialBlackKnight = Position.of(8, 2);
}
