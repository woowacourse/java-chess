package chess.domain.game;

import chess.domain.ChessBoard;
import chess.domain.Menu;
import chess.domain.position.Position;
import chess.domain.Result;
import chess.domain.Status;
import chess.domain.Turn;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private final ChessBoard chessBoard;
	private final Turn turn;
	private Menu menu;

	public ChessGame() {
		this.chessBoard = new ChessBoard(BoardFactory.createBoard());
		this.turn = new Turn();
	}

	public void play() {
		OutputView.printRule();
		initMenu();
		menu.validateStart();
		while (menu.isNotEnd()) {
			proceed();
			OutputView.printBoard(chessBoard);
			initMenu();
		}
	}

	private void proceed() {
		if (menu.isMove()) {
			Position startPosition = menu.getStartPosition();
			Position targetPosition = menu.getTargetPosition();
			turn.validateTurn(chessBoard.findByPosition(startPosition));
			chessBoard.move(startPosition, targetPosition);
			turn.changeTurn();
		}

		if (menu.isStatus()) {
			Status status = new Status(chessBoard.getBoard());
			Result result = status.getResult();
			OutputView.printResult(result);
		}
	}

	private void initMenu() {
		try {
			menu = new Menu(InputView.input());
		} catch (RuntimeException e) {
			OutputView.printErrorMessage(e);
			initMenu();
		}
	}

}
