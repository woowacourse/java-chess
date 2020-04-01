package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class Finished implements State {
	private Board board;
	private Turn turn;

	public Finished(Board board, Turn turn) {
		this.board = board;
		this.turn = turn;
	}

	@Override
	public State start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State end() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State move(Position source, Position target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Board board() {
		return board;
	}

	@Override
	public Score score(Color color) {
		return Score.calculate(board.findPiecesByColor(color));
	}

	@Override
	public Turn turn() {
		return turn;
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
