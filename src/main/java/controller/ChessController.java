package controller;

import static view.InputView.*;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.command.Command;
import domain.piece.team.Team;
import view.InputView;
import view.OutputView;

public class ChessController {
	public ChessController() {
		Board board = BoardFactory.create();
		Team turn = Team.WHITE;
		run(board, turn);
	}

	private void run(Board board, Team turn) {
		OutputView.printChessBoard(board);
		Command command;
		do {
			String inputChessCommand = InputView.inputCommand();
			String[] inputCommand = inputChessCommand.split(DELIMITER);
			command = Command.ofChessCommand(inputCommand[COMMAND_INDEX]);

			if (Command.MOVE.equals(command)) {
				board.move(inputCommand[SOURCE_POSITION], inputCommand[TARGET_POSITION], turn);
				OutputView.printChessBoard(board);
				turn = Team.changeTurn(turn);
			}

			if (Command.STATUS.equals(command)) {
				OutputView.printScore(board.calculateScore());
			}
		} while (Command.END != command && board.isKingAlive());
	}
}
