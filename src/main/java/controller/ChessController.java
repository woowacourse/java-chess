package controller;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.command.Command;
import domain.piece.team.Team;
import view.InputView;

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
		String inputChessCommand = InputView.inputCommand();
		String[] inputCommand = inputChessCommand.split(DELIMITER);

		Command command = Command.ofChessCommand(inputCommand[COMMAND_INDEX]);
		while (command != Command.END) {
			if (command == Command.MOVE) {
				board.move(inputCommand[SOURCE_POSITION], inputCommand[TARGET_POSITION], turn);
				turn = Team.changeTurn(turn);
			}
		}
	}
}
