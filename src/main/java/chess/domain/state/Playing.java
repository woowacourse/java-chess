package chess.domain.state;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

public class Playing extends Ready {
	protected final Board board;
	private Turn turn;

	public Playing(Board board, Turn turn) {
		this.board = board;
		this.turn = turn;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public ChessGameState start() {
		throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
	}

	@Override
	public ChessGameState move(Position source, Position target) {
		board.move(source, target, turn);
		if (board.isKingDead()) {
			return new Finish(board);
		}
		turn = turn.switchTurn();
		return this;
	}

	@Override
	public ChessGameState end() {
		return new Finish(board);
	}

	@Override
	public Board board() {
		return board;
	}

	@Override
	public Score score(Team team) {
		return Score.calculate(board.findPiecesByTeam(team));
	}

	@Override
	public Turn turn() {
		return turn;
	}
}
