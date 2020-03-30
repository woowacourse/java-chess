package chess.controller;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.command.Command;
import chess.domain.command.FirstCommand;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Controller {
	public static void run() {
		start();
		running();
		end();
	}

	private static void start() {
		OutputView.printGameInstruction();
		FirstCommand command = FirstCommand.of(InputView.inputCommand());

		if (command.isEnd()) {
			end();
		}
	}

	private static void running() {
		Command command;
		Map<Position, Piece> pieces = new HashMap<>();
		ChessBoard chessBoard = new ChessBoard(pieces);
		Color color = Color.WHITE;

		OutputView.printChessBoard(pieces);

		do {
			command = readCommand();
			if (command.isMove()) {
				color = moveTurn(color, command, chessBoard);
			}

			if (command.isStatus()) {
				OutputView.printStatus(pieces);
			}
		} while (command.isEnd() || !chessBoard.isKingNotDead(color));

		OutputView.printStatus(pieces);
		end();
	}

	private static Color moveTurn(Color color, Command command, ChessBoard chessBoard) {
		try {
			chessBoard.moveFromTo(color, command.getMoveSource(), command.getMoveTarget());
			return changeColor(color);
		} catch (UnsupportedOperationException e) {
			OutputView.printException(e);
		}
		return color;
	}

	private static Color changeColor(Color color) {
		return Color.reverse(color);
	}

	private static void end() {
		OutputView.printGameEnd();
		System.exit(0);
	}

	private static Command readCommand() {
		try {
			return new Command(InputView.inputCommand());
		} catch (IllegalArgumentException e) {
			OutputView.printException(e);
			return readCommand();
		}
	}
}
