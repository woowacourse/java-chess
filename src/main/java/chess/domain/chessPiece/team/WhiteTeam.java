package chess.domain.chessPiece.team;

public class WhiteTeam implements TeamStrategy {
	private static final String WHITE_PAWN_NAME = "P";
	private static final String WHITE_KING_NAME = "K";
	private static final String WHITE_QUEEN_NAME = "Q";
	private static final String WHITE_BISHOP_NAME = "B";
	private static final String WHITE_KNIGHT_NAME = "N";
	private static final String WHITE_ROOK_NAME = "R";

	@Override
	public String pawnName() {
		return WHITE_PAWN_NAME;
	}

	@Override
	public String kingName() {
		return WHITE_KING_NAME;
	}

	@Override
	public String queenName() {
		return WHITE_QUEEN_NAME;
	}

	@Override
	public String bishopName() {
		return WHITE_BISHOP_NAME;
	}

	@Override
	public String knightName() {
		return WHITE_KNIGHT_NAME;
	}

	@Override
	public String rookName() {
		return WHITE_ROOK_NAME;
	}

	@Override
	public boolean isBlackTeam() {
		return false;
	}

	@Override
	public boolean isWhiteTeam() {
		return true;
	}

	@Override
	public boolean isSameTeam(TeamStrategy teamStrategy) {
		return teamStrategy instanceof WhiteTeam;
	}

	@Override
	public String toString() {
		return "white";
	}
}
