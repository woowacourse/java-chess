package chess.domain.state;

import chess.domain.ChessScore;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;

public abstract class State {

	protected Board board;

	public abstract State proceed(Command command);

	public abstract boolean isFinished();

	public abstract ChessScore generateScore();

	public abstract boolean isRunning();

	public abstract Color getColor();

	public Board getBoard() {
		return board;
	}
}
