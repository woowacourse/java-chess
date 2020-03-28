package chess.controller;

import java.util.Arrays;
import java.util.List;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	private static final String MOVE = "move";
	private static final String DELIMITER = " ";
	private static final String STATUS = "status";
	private static final String END = "end";
	private static final int COMMAND_ARGUMENTS_SIZE = 3;
	private State state = State.BEFORESTART;

	public void run() {
		OutputView.printGameStartInstruction();
		Board board = new Board();
		setInitialStatus();

		while (state == State.RUNNING && board.isBothKingAlive()) {
			OutputView.printChessBoard(board);
			processSingleTurn(board);
		}
		if (!board.isBothKingAlive()) {
			OutputView.printWinner(board.getWinner());
		}
	}

	private void setInitialStatus() {
		try {
			state = State.of(InputView.inputGameStartOrEnd());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			setInitialStatus();
		}
	}

	private void processSingleTurn(Board board) {
		try {
			String decision = InputView.inputInstruction();
			processCommand(board, decision);
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private void processCommand(Board board, String decision) {
		if (decision.split(DELIMITER)[0].equals(MOVE)) {
			List<String> multiArguments = Arrays.asList(decision.split(DELIMITER));
			validateMultiArguments(multiArguments);
			Position source = new Position(multiArguments.get(1));
			Position destination = new Position(multiArguments.get(2));
			board.movePiece(source, destination);
			return;
		}
		if (decision.equals(STATUS)) {
			OutputView.printScore(board);
			OutputView.printTeamWithHigherScore(board);
			return;
		}
		if (decision.equals(END)) {
			state = State.FINISHED;
			return;
		}
		throw new IllegalArgumentException("올바른 명령을 입력해 주십시오.");
	}

	private void validateMultiArguments(List<String> multiArguments) {
		if (multiArguments.size() != COMMAND_ARGUMENTS_SIZE) {
			throw new IllegalArgumentException("명령에 인자가 부족합니다.");
		}
	}
}
