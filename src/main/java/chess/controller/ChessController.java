package chess.controller;

import chess.ChessGame;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printGameStart();
		ChessGame service = ChessGame.newGame();
		while (!service.isEnd()) {
			runGame(service);
		}
	}

	private void runGame(ChessGame service) {
		try {
			RequestDto request = InputView.inputRequest();
			ResponseDto response = service.play(request);
			OutputView.printResponse(response);
		} catch (Exception e) {
			OutputView.printException(e);
		}
	}
}
