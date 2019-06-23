package chess.domain;

public interface Rule {

    boolean isValidMove(final Position origin, final Position target);

    boolean isValidAttack(final Position origin, final Position target);

    double getScore();

    String getName();
}
