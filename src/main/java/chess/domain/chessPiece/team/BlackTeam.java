package chess.domain.chessPiece.team;

public class BlackTeam implements TeamStrategy {
	private static final String BLACK_PAWN_NAME = "p";
	private static final String BLACK_KING_NAME = "k";
	private static final String BLACK_QUEEN_NAME = "q";
	private static final String BLACK_BISHOP_NAME = "b";
	private static final String BLACK_KNIGHT_NAME = "n";
	private static final String BLACK_ROOK_NAME = "r";

	@Override
	public String pawnName() {
		return BLACK_PAWN_NAME;
	}

	@Override
	public String kingName() {
		return BLACK_KING_NAME;
	}

	@Override
	public String queenName() {
		return BLACK_QUEEN_NAME;
	}

	@Override
	public String bishopName() {
		return BLACK_BISHOP_NAME;
	}

	@Override
	public String knightName() {
		return BLACK_KNIGHT_NAME;
	}

	@Override
	public String rookName() {
		return BLACK_ROOK_NAME;
	}

	@Override
	public boolean isBlackTeam() {
		return true;
	}

	@Override
	public boolean isWhiteTeam() {
		return false;
	}

	@Override
	public boolean isSameTeam(TeamStrategy teamStrategy) {
		return teamStrategy instanceof BlackTeam;
	}

	@Override
	public String toString() {
		return "black";
	}
}
