package chess.domain.RuleImpl;

import chess.domain.Position;

public abstract class AbstractRule implements Rule {
	private final double score;
	private final String name;

	AbstractRule(final double score, final String name) {
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
	public double getScore() {
		return score;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
