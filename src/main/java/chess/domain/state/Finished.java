package chess.domain.state;

import java.util.Map;

import chess.domain.board.ChessScore;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class Finished extends GameState {

	private static final String CANNOT_PROCEED = "끝난 상태에서는 진행할 수 없습니다.";
	private static final String CANNOT_GENERATE_SCORE = "게임이 끝난 후에는 점수를 불러올 수 없습니다.";
	private static final String CANNOT_GET_COLOR = "끝난 상태에서는 색을 불러올 수 없습니다.";

	public Finished(Map<Position, Piece> pieces) {
		this.board = new Board(pieces);
	}

	@Override
	public GameState proceed(Command command) {
		throw new UnsupportedOperationException(CANNOT_PROCEED);
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public ChessScore generateScore() {
		throw new UnsupportedOperationException(CANNOT_GENERATE_SCORE);
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public Color getColor() {
		throw new UnsupportedOperationException(CANNOT_GET_COLOR);
	}

	@Override
	public String toString() {
		return "FINISHED";
	}
}
