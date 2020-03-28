package chess;

import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import chess.state.ChessGameState;
import chess.state.Command;
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
		Command command = request.getCommand();
		if (command == Command.MOVE) {
			state = request.getCommand().move(state, request.getParameters());
			return state.getResponse();
		}
		state = request.getCommand().run(state);
		return state.getResponse();
	}
}
