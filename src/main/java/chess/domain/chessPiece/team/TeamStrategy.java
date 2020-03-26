package chess.domain.chessPiece.team;

public interface TeamStrategy {
	String pawnName();

	String kingName();

	String queenName();

	String bishopName();

	String knightName();

	String rookName();

	boolean isBlackTeam();
}
