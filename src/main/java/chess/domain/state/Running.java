package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.score.ScoreCalculator;
import chess.domain.score.ScoreResult;

public abstract class Running implements State {

	protected static final String WRONG_SOURCE_ERROR = "상대 팀의 기물을 옮길 수 없습니다.";

	private static final String NOT_FINISHED_ERROR = "아직 종료되지 않은 게임입니다.";
	private static final String ALREADY_GAME_START = "게임이 이미 시작되었습니다.";
	private static final String SAME_POSITION_ERROR = "출발지와 같은 곳으로 이동할 수 없습닌다.";

	protected final Board board;

	public Running(final Board board) {
		this.board = board;
	}

	@Override
	public final State start(final Board board) {
		throw new IllegalStateException(ALREADY_GAME_START);
	}

	@Override
	public final State move(final Position source, final Position target) {
		if (source.equals(target)) {
			throw new IllegalArgumentException(SAME_POSITION_ERROR);
		}
		validateTurn(source);
		boolean check = board.isCheck(target);
		board.move(source, target);
		return getNextTurn(check);
	}

	@Override
	public final ScoreResult createStatus() {
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		double blackScore = scoreCalculator.calculate(board, Team.BLACK);
		double whiteScore = scoreCalculator.calculate(board, Team.WHITE);
		return new ScoreResult(blackScore, whiteScore);
	}

	@Override
	public final State finish() {
		return new EndGame(board, Team.NEUTRALITY);
	}

	@Override
	public final boolean isFinished() {
		return false;
	}

	@Override
	public final Team judgeWinner() {
		throw new IllegalStateException(NOT_FINISHED_ERROR);
	}

	@Override
	public final Board getBoard() {
		return board;
	}

	protected abstract void validateTurn(final Position position);

	protected abstract State getNextTurn(boolean check);
}
