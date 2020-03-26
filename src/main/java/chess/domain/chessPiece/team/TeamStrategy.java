package chess.domain.chessPiece.team;

public interface TeamStrategy {
	String pawnName();

	String kingName();

	String queenName();

	String bishopName();

	String knightName();

	String rookName();

	boolean isBlackTeam();

	boolean isWhiteTeam();

	boolean isSameTeam(TeamStrategy teamStrategy);
}
