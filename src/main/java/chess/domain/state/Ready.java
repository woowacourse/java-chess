package chess.domain.state;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class Ready extends State {

	protected Ready(Map<Position, Piece> board) {
		this.board = new Board(board);
	}

	@Override
	public State proceed(Command command) {
		if (command.isMove()) {
			throw new IllegalArgumentException();
		}
		if (command.isStart()) {
			return new RunningWhiteTurn(board.getPieces());
		}
		return new Finished(board.getPieces());
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
