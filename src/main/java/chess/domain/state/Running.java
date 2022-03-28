package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public abstract class Running implements State {

	private static final String WRONG_SOURCE_ERROR = "상대 팀의 기물을 옮길 수 없습니다.";
	private static final String WRONG_TARGET_ERROR = "같은 팀의 기물로 이동할 수 없습니다.";

	private final Team team;

	protected Running(Team team) {
		this.team = team;
	}

	@Override
	public final boolean isFinished() {
		return false;
	}

	@Override
	public final State play(final Piece source, final Piece target) {
		validateSourcePiece(source);
		validateTargetPiece(target);
		if (target.isKing()) {
			return new KingDeath(team);
		}
		return getNextTurn();
	}

	private void validateSourcePiece(final Piece piece) {
		if (!piece.isAlly(team)) {
			throw new IllegalArgumentException(WRONG_SOURCE_ERROR);
		}
	}

	private void validateTargetPiece(final Piece piece) {
		if (piece.isAlly(team)) {
			throw new IllegalArgumentException(WRONG_TARGET_ERROR);
		}
	}

	@Override
	public final State finish() {
		return new EndGame(Team.NEUTRALITY);
	}

	@Override
	public final Team getTeam() {
		return team;
	}

	protected abstract State getNextTurn();
}
