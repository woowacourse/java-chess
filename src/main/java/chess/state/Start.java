package chess.state;

import java.util.List;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;
import chess.dto.ResponseDto;

public class Start extends Ready {
	protected final Board board;
	private Turn turn;

	public Start(Board board) {
		this.board = board;
		this.turn = new Turn(Team.WHITE);
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
	public ChessGameState move(List<String> parameters) {
		board.move(Position.of(parameters.get(0)), Position.of(parameters.get(1)), turn);
		if (board.isKingDead()) {
			return new Finish(board, turn);
		}
		turn.switchTurn();
		return this;
	}

	@Override
	public ChessGameState end() {
		// status();
		// turn = board.getWinner();
		return new Finish(board, turn);
	}

	@Override
	public ResponseDto getResponse() {
		return new ResponseDto(board.getDto());
	}
}
