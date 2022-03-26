package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public abstract class Finished implements State {

	private final Team winner;

	public Finished(final Team winner) {
		this.winner = winner;
	}

	@Override
	public final boolean isFinished() {
		return true;
	}

	@Override
	public final State play(Piece source, Piece target) {
		throw new IllegalArgumentException("현재는 게임을 진행할 수 없습니다.");
	}

	@Override
	public final Team getTeam() {
		return winner;
	}
}
