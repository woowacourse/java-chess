package chess.domain.state;

import java.util.List;

import chess.dto.ResponseDto;

public interface ChessGameState {

	boolean isEnd();

	ChessGameState start();

	ChessGameState move(List<String> parameters);

	ChessGameState end();

	default void exception() {
		throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
	}

	ResponseDto getResponse();
}
