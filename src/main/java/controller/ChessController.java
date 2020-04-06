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
		Team turn = Team.WHITE;
		run(game, turn);
	}

	private void run(BoardGame board, Team turn) {
		OutputView.printChessBoard(board.getReverse());
		Command command;
		do {
			String inputCommand = inputCommand();
			command = Command.of(inputCommand);
			if (command.isMove()) {
				MoveCommand moveCommand = new MoveCommand(inputCommand);
				board.move(moveCommand, turn);
				OutputView.printChessBoard(board.getReverse());
				turn = Team.changeTurn(turn);
			}

			if (command.isStatus()) {
				Map<Team, Double> score = Score.calculateScore(board.getRanks(), Team.values());
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