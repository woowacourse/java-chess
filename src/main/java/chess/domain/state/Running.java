package chess.domain.state;

import java.util.Map;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public abstract class Running extends State {

	protected static final String CANNOT_MOVE_OPPONENT_PIECE = "상대 말을 움지이게 할 수 없습니다.";
	private static final String CANNOT_START_AGAIN = "게임이 시작된 이후에 또 시작할 수 없습니다.";

	protected Running(Map<Position, Piece> pieces) {
		this.board = new Board(pieces);
	}

	@Override
	public abstract State proceed(Command command);

	@Override
	public boolean isFinished() {
		return false;
	}

	protected State checkFinished(Command command) {
		if (command.isStart()) {
			throw new IllegalArgumentException(CANNOT_START_AGAIN);
		}
		if (command.isStatus()) {
			return this;
		}
		return new Finished(board.getPieces());
	}

	@Override
	public ChessScore generateScore() {
		return this.board.calculateScore();
	}
}
