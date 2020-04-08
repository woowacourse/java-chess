package chess.contoller;

import static chess.view.InputView.*;
import static chess.view.OutputView.*;

import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.command.Command;

public class ConsoleController {
	public void run() {
		printInitMessage();
		ChessGame chessGame = ChessGame.start();
		while (!chessGame.isEnd()) {
			printInitBoard(chessGame.getBoard());
			printScore(chessGame.status(Side.BLACK), chessGame.status(Side.WHITE));
			chessGame = Command.execute(inputCommand(), chessGame);
		}
	}
}
