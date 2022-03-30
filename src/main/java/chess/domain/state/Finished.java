package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.score.ScoreResult;

public abstract class Finished implements State {

	private static final String FINISH_GAME_ERROR = "게임이 이미 종료되었습니다.";

	private final Board board;
	private final Team winner;

	public Finished(final Board board, final Team winner) {
		this.board = board;
		this.winner = winner;
	}

	@Override
	public final State start(final Board board) {
		throw new IllegalArgumentException(FINISH_GAME_ERROR);
	}

	@Override
	public final State play(final Position source, final Position target) {
		throw new IllegalArgumentException(FINISH_GAME_ERROR);
	}

	@Override
	public final ScoreResult createStatus() {
		throw new IllegalArgumentException(FINISH_GAME_ERROR);
	}

	@Override
	public final State finish() {
		throw new IllegalArgumentException(FINISH_GAME_ERROR);
	}

	@Override
	public final boolean isFinished() {
		return true;
	}

	@Override
	public final Team judgeWinner() {
		return winner;
	}

	@Override
	public final Board getBoard() {
		return board;
	}
}
