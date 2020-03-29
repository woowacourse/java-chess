package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;

import static chess.controller.InGameDecision.END;

public class ChessController {

	public synchronized void run() {
		OutputView.printGameStartInstruction();
		Board board = new Board();

		String decision = InputView.inputGameStartOrEnd();
		StartDecision startDecision = StartDecision.of(decision);
		if (startDecision == StartDecision.START) {
			playGame(board);
		}
	}

	private void playGame(Board board) {
		InGameDecision inGameDecision;
		do {
			OutputView.printChessBoard(board);
			inGameDecision = processSingleTurn(board);
		} while (inGameDecision != END && board.isBothKingAlive());
		if (!board.isBothKingAlive()) {
			OutputView.printWinner(board.getWinner());
		}
	}

	private InGameDecision processSingleTurn(Board board) {
		try {
			String decision = InputView.inputInstruction();
			InGameDecision inGameDecision = InGameDecision.of(board, decision);
			inGameDecision.getAction().accept(board, Arrays.asList(decision.split(" ")));
			return inGameDecision;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return processSingleTurn(board);
		}
	}
}
