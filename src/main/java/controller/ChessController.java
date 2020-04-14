package controller;

import static view.InputView.*;

import java.util.Map;

import domain.Score;
import domain.board.BoardGame;
import domain.command.Command;
import domain.command.InvalidCommandException;
import domain.command.MoveCommand;
import domain.piece.team.Team;
import view.OutputView;

public class ChessController {
	ChessController() {
		BoardGame game = new BoardGame();
		run(game);
	}

	private void run(BoardGame board) {
		OutputView.printChessBoard(board.getReverse());
		Command command;
		do {
			String inputCommand = inputCommand();
			command = Command.of(inputCommand);
			if (command.isMove()) {
				MoveCommand moveCommand = new MoveCommand(inputCommand);
				board.move(moveCommand);
				OutputView.printChessBoard(board.getReverse());
			}

			if (command.isStatus()) {
				Map<Team, Double> score = Score.calculateScore(board.getPieces(), Team.values());
				OutputView.printScore(score);
			}

			if (command.isStart()) {
				throw new InvalidCommandException(InvalidCommandException.INVALID_COMMAND_TYPE);
			}
		} while (command.isNotEnd() && board.isKingAlive());
		OutputView.printGameIsEnd();
		System.exit(0);
	}
}