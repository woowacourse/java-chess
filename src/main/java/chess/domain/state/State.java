package chess.domain.state;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public abstract class State {

	protected Board board;

	public static State create() {
		return new Ready();
	}

	public static State create(Map<Position, Piece> board) {
		return new Ready(board);
	}

	public abstract State proceed(Command command);

	public Board getBoard() {
		return board;
	}
}
