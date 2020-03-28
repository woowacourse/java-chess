package chess.controller;

import chess.ChessService;
import chess.dto.RequestDto;
import chess.dto.ResponseDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printGameStart();
		ChessService service = ChessService.newGame();

		while (!service.isEnd()) {
			try {
				RequestDto request = InputView.inputRequest();
				ResponseDto response = service.play(request);
				OutputView.printResponse(response);
			} catch (Exception e) {
				OutputView.printException(e);
			}
		}
	}
}
