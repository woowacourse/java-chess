package controller;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.command.Command;
import domain.piece.team.Team;
import view.InputView;
import view.OutputView;

public class ChessController {
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_POSITION = 1;
	private static final int TARGET_POSITION = 2;
	private static final String DELIMITER = " ";

	public ChessController() {
		Board board = BoardFactory.create();
		Team turn = Team.WHITE;
		run(board, turn);
	}

	private void run(Board board, Team turn) {
		OutputView.printChessBoard(board);
		try {
			executeCommand(board, turn);
			OutputView.printChessBoard(board);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			run(board, turn);
		}
	}

	private void executeCommand(Board board, Team turn) {
		Command command;
		String[] inputCommand;
		do {
			OutputView.printTurn(turn);
			inputCommand = InputView.inputCommand().split(DELIMITER);
			command = Command.ofChessCommand(inputCommand[COMMAND_INDEX]);
			if (command == Command.MOVE) {
				board.move(inputCommand[SOURCE_POSITION], inputCommand[TARGET_POSITION], turn);
				turn = Team.changeTurn(turn);
			}
			if (command == Command.STATUS) {
				OutputView.printTeamScore(board.calculateTeamScore(Team.WHITE), board.calculateTeamScore(Team.BLACK));
			}
			OutputView.printChessBoard(board);
		} while (command != Command.END);
	}
}
