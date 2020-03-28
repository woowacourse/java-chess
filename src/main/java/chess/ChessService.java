package chess;

import chess.dto.ResponseDto;
import chess.state.ChessGameState;
import chess.state.Ready;

public class ChessService {
	private ChessGameState state;

	private ChessService() {
		this.state = new Ready();
	}

	public static ChessService newGame() {
		return new ChessService();
	}

	public boolean isEnd() {
		return state.isEnd();
	}

	public ResponseDto play(RequestDto request) {
		return null;
	}
}
