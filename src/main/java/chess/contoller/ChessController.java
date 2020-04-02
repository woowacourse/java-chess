package chess.contoller;

import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.command.Command;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printInitMessage();
		ChessGame chessGame = ChessGame.start();
		while (!chessGame.isEnd()) {
			printLog(chessGame);
			chessGame = Command.execute(InputView.inputCommand(), chessGame);
		}
	}

	private void printLog(ChessGame chessGame) {
		System.out.println();
		OutputView.createEmptyBoard(Column.values().length, Row.values().length);
		OutputView.printInitBoard(chessGame.getBoard());
		OutputView.printScore(chessGame.status(Side.BLACK), chessGame.status(Side.WHITE));
	}
}
