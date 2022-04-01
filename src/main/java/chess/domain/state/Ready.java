package chess.domain.state;

import java.util.Map;

import chess.domain.board.ChessScore;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class Ready extends GameState {

	private static final String CANNOT_MOVE = "게임 시작 전에는 움직일 수 없습니다.";
	private static final String CANNOT_GENERATE_SCORE = "게임 시작 전에는 점수를 불러올 수 없습니다.";
	private static final String CANNOT_GET_COLOR = "게임 시작 전에는 색을 불러올 수 없습니다.";

	public Ready(Map<Position, Piece> board) {
		this.board = new Board(board);
	}

	@Override
	public GameState proceed(Command command) {
		if (command.isMove()) {
			throw new IllegalArgumentException(CANNOT_MOVE);
		}
		if (command.isStart()) {
			return new RunningWhiteTurn(board.getPieces());
		}
		if (command.isStatus()) {
			throw new IllegalArgumentException(CANNOT_GENERATE_SCORE);
		}
		return new Finished(board.getPieces());
	}

	@Override
	public boolean isFinished() {
		return false;
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
}
