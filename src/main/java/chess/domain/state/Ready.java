package chess.domain.state;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

public class Ready implements ChessGameState {
	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public ChessGameState start() {
		return new Playing(BoardRepository.create(), new Turn(Team.WHITE));
	}

	@Override
	public ChessGameState move(Position source, Position target) {
		throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
	}

	@Override
	public ChessGameState end() {
		throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
	}

	@Override
	public Board board() {
		throw new UnsupportedOperationException("게임이 아직 준비중입니다.");
	}

	@Override
	public Score score(Team team) {
		throw new UnsupportedOperationException("게임이 아직 준비중입니다.");
	}

	@Override
	public Turn turn() {
		throw new UnsupportedOperationException();
	}
}
