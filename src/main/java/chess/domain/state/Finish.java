package chess.domain.state;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.position.Position;

public class Finish implements ChessGameState {
	private final Board board;

	public Finish(Board board) {
		this.board = board;
	}

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public ChessGameState start() {
		throw new UnsupportedOperationException("게임이 종료되었습니다.");
	}

	@Override
	public ChessGameState move(Position source, Position target) {
		throw new UnsupportedOperationException("게임이 종료되었습니다.");
	}

	@Override
	public ChessGameState end() {
		throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
	}

	@Override
	public Board board() {
		return board;
	}

	@Override
	public Score score(Team team) {
		return Score.calculate(board.findPiecesByTeam(team));
	}
}
