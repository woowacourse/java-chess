package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public abstract class Finished implements State {

	private static final String GAME_PLAY_ERROR = "현재는 게임을 진행할 수 없습니다.";
	private static final String FINISH_GAME_ERROR = "게임이 이미 종료되었습니다.";

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
		throw new IllegalArgumentException(GAME_PLAY_ERROR);
	}

	@Override
	public State finish() {
		throw new IllegalArgumentException(FINISH_GAME_ERROR);
	}

	@Override
	public final Team getTeam() {
		return winner;
	}
}
