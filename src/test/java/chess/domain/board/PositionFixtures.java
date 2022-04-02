package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;

public class PositionFixtures {

	public static Position initialWhiteKing = Position.of(ONE, E);
	public static Position initialWhiteQueen = Position.of(ONE, D);
	public static Position initialWhiteBishop = Position.of(ONE, C);
	public static Position initialWhiteKnight = Position.of(ONE, B);
	public static Position initialWhiteRook = Position.of(ONE, A);
	public static Position initialWhitePawn = Position.of(TWO, A);

	public static Position initialBlackKing = Position.of(EIGHT, E);
	public static Position initialBlackQueen = Position.of(EIGHT, D);
	public static Position initialBlackKnight = Position.of(EIGHT, B);
	public static Position initialBlackPawn = Position.of(SEVEN, A);
}
