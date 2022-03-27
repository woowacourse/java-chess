package chess.domain.game;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class ChessGame {

	private State state;

	public ChessGame() {
		state = new Start();
	}

	public void start() {
		state = state.start();
	}

	public void move(Coordinate from, Coordinate to) {
		state = state.move(from, to);
	}

	public void end() {
		state = state.end();
	}

	public boolean isFinished() {
		return state.isFinished();
	}
	
	public Map<Coordinate, Piece> getValue() {
		return state.getValue();
	}
}
