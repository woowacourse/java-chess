package chess.state;

import java.util.List;

import chess.domain.Board;
import chess.dto.ResponseDto;

public class Ready implements ChessGameState {
	protected final Board board;

	public Ready() {
		this.board = BoardRepository.create();
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public ChessGameState start() {
		return new Start(board);
	}

	@Override
	public ChessGameState move(List<String> parameters) {
		throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
	}

	@Override
	public ChessGameState end() {
		throw new UnsupportedOperationException("아직 게임이 시작되지 않았습니다.");
	}

	@Override
	public ResponseDto getResponse() {
		return new ResponseDto(board.getDto());
	}
}
