package chess.domain.moverule;

import chess.domain.MoveRule;
import chess.domain.Position;

public abstract class AbstractMoveRule implements MoveRule {
	private final double score;
	private final String name;

    AbstractMoveRule(final double score, final String name) {
		this.score = score;
		this.name = name;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return false;
	}

	@Override
	public boolean isValidAttack(final Position origin, final Position target) {
		return isValidMove(origin, target);
	}

    @Override
    public boolean isSameName(final String name) {
        return this.name.equals(name);
    }

    @Override
	public double getScore() {
		return score;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
