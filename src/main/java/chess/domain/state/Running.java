package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;

public abstract class Running implements State {

	protected static final String WRONG_SOURCE_ERROR = "상대 팀의 기물을 옮길 수 없습니다.";

	private static final String NOT_FINISHED_ERROR = "아직 종료되지 않은 게임입니다.";

	protected final Board board;

	public Running(final Board board) {
		this.board = board;
	}

	@Override
	public final State start(final Board board) {
		throw new IllegalArgumentException();
	}

	@Override
	public final State play(final Position source, final Position target) {
		validateTurn(board.getBoard().get(source));
		board.move(source, target);
		return getNextTurn(board.getBoard().get(target).isKing());
	}

	@Override
	public final StatusResult createStatus() {
		double blackScore = board.calculateScore(Team.BLACK);
		double whiteScore = board.calculateScore(Team.WHITE);
		return new StatusResult(blackScore, whiteScore);
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
		throw new IllegalArgumentException(NOT_FINISHED_ERROR);
	}

	@Override
	public final Board getBoard() {
		return board;
	}

	protected abstract void validateTurn(final Piece piece);

	protected abstract State getNextTurn(boolean kingDeath);
}
