package chess.domain.chessgame;

import chess.domain.Result;
import chess.domain.Status;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private final ChessBoard chessBoard;
	private ChessMenu chessMenu;

	public ChessGame() {
		this.chessBoard = BoardFactory.createBoard();
	}

	public void play() {
		OutputView.printRule();
		decideMenu();
		chessMenu.validateStart();
		OutputView.printBoard(chessBoard);

		while (isContinue()) {
			decideMenu();
			playRound();
		}
		OutputView.printEndGame();
	}

	private void decideMenu() {
		while (isNotAllowedMenu());
	}

	private boolean isNotAllowedMenu() {
		try {
			chessMenu = new ChessMenu(InputView.inputMenu());
			return false;
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			OutputView.printErrorMessage(e);
			return true;
		}
	}

	private boolean isContinue() {
		return chessMenu.isNotEnd() && chessBoard.isLiveBothKing();
	}

	private void playRound() {
		try {
			proceed();
			OutputView.printBoard(chessBoard);
		} catch (Exception e) {
			OutputView.printErrorMessage(e);
		}
	}

	private void proceed() {
		if (chessMenu.isMove()) {
			Position startPosition = chessMenu.createStartPosition();
			Position targetPosition = chessMenu.createTargetPosition();
			chessBoard.move(startPosition, targetPosition);
		}

		if (chessMenu.isStatus()) {
			Status status = chessBoard.createStatus();
			Result result = status.getResult();
			OutputView.printResult(result);
		}
	}
}
