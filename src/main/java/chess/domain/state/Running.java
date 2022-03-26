package chess.domain.state;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public abstract class Running extends State {

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
			throw new IllegalArgumentException();
		}
		return new Finished(board.getPieces());
	}
}
