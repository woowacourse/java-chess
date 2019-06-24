package chess.domain;

public interface MoveRule {

	boolean isValidMove(final Position origin, final Position target);

	boolean isValidAttack(final Position origin, final Position target);

	boolean isSameName(final String name);

	double getScore();

	String getName();
}
