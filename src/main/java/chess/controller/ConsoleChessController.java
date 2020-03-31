package chess.controller;

import chess.domain.Board;
import chess.domain.InGameDecision;
import chess.domain.StartDecision;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chess.domain.InGameDecision.END;
import static chess.domain.InGameDecision.STATUS;

public class ConsoleChessController implements ChessController {

	@Override
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
			inGameDecision = playSingleTurn(board);
		} while (inGameDecision != END && board.isBothKingAlive());
		if (!board.isBothKingAlive()) {
			OutputView.printWinner(board.getWinner());
		}
	}

	private InGameDecision playSingleTurn(Board board) {
		InGameDecision inGameDecision;
		OutputView.printChessBoard(board);
		inGameDecision = processSingleTurn(board);
		if(inGameDecision == STATUS) {
			printStatus(board);
		}
		return inGameDecision;
	}

	private InGameDecision processSingleTurn(Board board) {
		try {
			String decision = InputView.inputInstruction();
			List<String> multiArguments = new ArrayList<>(Arrays.asList(decision.split(" ")));
			InGameDecision inGameDecision = InGameDecision.of(multiArguments.get(0));
			inGameDecision.getAction().accept(board, multiArguments);
			return inGameDecision;
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return processSingleTurn(board);
		}
	}

	private void printStatus(Board board) {
		OutputView.printScore(board);
		OutputView.printTeamWithHigherScore(board);
	}
}
