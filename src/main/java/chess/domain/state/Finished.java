package chess.domain.state;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class Finished extends State {

	public Finished(Map<Position, Piece> pieces) {
		this.board = new Board(pieces);
	}

	@Override
	public State proceed(Command command) {
		throw new IllegalStateException();
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
