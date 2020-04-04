package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private final ChessBoard chessBoard;
	private Command command;

	public ChessGame() {
		this.chessBoard = BoardFactory.createBoard();
	}

	public void play() {
		OutputView.printRule();
		initMenu();
		command.validateStart();
		OutputView.printBoard(chessBoard);

		while (command.isNotEnd() && chessBoard.isLiveBothKing()) {
			initMenu();
			playRound();
		}
		OutputView.printEndGame();
	}

	private void initMenu() {
		while (isNotAllowedMenu());
	}

	private boolean isNotAllowedMenu() {
		try {
			command = new Command(InputView.inputMenu());
			return false;
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			OutputView.printErrorMessage(e);
			return true;
		}
	}

	private void playRound() {
		try {
			command.execute(chessBoard);
			OutputView.printBoard(chessBoard);
		} catch (Exception e) {
			OutputView.printErrorMessage(e);
		}
	}
}
