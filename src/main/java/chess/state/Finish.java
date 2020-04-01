package chess.state;

import java.util.List;

import chess.domain.Board;
import chess.domain.Turn;
import chess.dto.ResponseDto;

public class Finish extends Start {
	private final Turn turn;

	public Finish(Board board, Turn turn) {
		super(board);
		this.turn = turn;
	}

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public ChessGameState start() {
		ChessGameState nextState = new Ready();
		return nextState.start();
	}

	@Override
	public ChessGameState move(List<String> parameters) {
		throw new UnsupportedOperationException("게임이 종료되었습니다.");
	}

	@Override
	public ChessGameState end() {
		throw new UnsupportedOperationException("게임이 이미 종료되었습니다.");
	}

	@Override
	public ResponseDto getResponse() {
		return new ResponseDto(board.getDto(), turn);
	}
}
