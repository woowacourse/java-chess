package chess;

import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
	public static void main(String[] args) {
		ChessGame chessGame = new ChessGame(new Ready());
		OutputView.printGameStart();
		while (!chessGame.isFinished()) {
			chessGame.command(InputView.inputCommand());
			OutputView.printBoard(chessGame.board());
		}
	}
}
