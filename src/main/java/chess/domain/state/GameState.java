package chess.domain.state;

import java.util.Map;

import chess.domain.board.ChessScore;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class GameState {

	protected Board board;

	public abstract GameState proceed(Command command);

	public abstract boolean isFinished();

	public abstract ChessScore generateScore();

	public abstract boolean isRunning();

	public abstract Color getColor();

	public Map<Position, Piece> getBoard() {
		return board.getPieces();
	}
}
