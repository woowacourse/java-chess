package controller;

import domain.board.Board;
import domain.board.BoardFactory;
import domain.command.ChessCommand;
import domain.piece.team.Team;
import view.InputView;
import view.OutputView;

public class ChessController {
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_POSITION = 1;
	private static final int TARGET_POSITION = 2;
	private static final String DELIMITER = " ";

	private Board board = BoardFactory.create();
	private Team team = Team.WHITE;

	public ChessController() {
		run(board);
	}

	private void run(Board board) {
		try {
			OutputView.printChessBoard(board);
			executeCommand(board);
			OutputView.printChessBoard(board);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			run(board);
		}
	}

	private void executeCommand(Board board) {
		ChessCommand chessCommand;
		String[] inputCommand;
		do {
			checkKingDead(board);
			OutputView.printTurn(team);
			OutputView.printChessCommand();
			inputCommand = InputView.inputCommand().split(DELIMITER);
			chessCommand = ChessCommand.ofChessCommand(inputCommand[COMMAND_INDEX]);
			if (chessCommand == ChessCommand.MOVE) {
				board.move(inputCommand[SOURCE_POSITION], inputCommand[TARGET_POSITION], team);
				team = Team.changeTurn(team);
			}
			if (chessCommand == ChessCommand.STATUS) {
				OutputView.printTeamScore(board.calculateTeamScore(Team.WHITE), board.calculateTeamScore(Team.BLACK));
			}
			OutputView.printChessBoard(board);
		} while (chessCommand != ChessCommand.END);
	}

	private void checkKingDead(Board board) {
		if (board.isKingDead()) {
			OutputView.printWinner(board.findWinner());
			System.exit(0);
		}
	}
}
