package chess.state;

import java.util.List;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.Turn;
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
		return super.move(parameters);
	}

	@Override
	public ChessGameState end() {
		return new Finish(board);
	}

	@Override
	public ResponseDto getResponse() {
		return new ResponseDto(board.getDto());
	}
}
