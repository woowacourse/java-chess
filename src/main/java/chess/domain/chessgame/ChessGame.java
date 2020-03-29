package chess.domain.chessgame;

import chess.domain.Result;
import chess.domain.Status;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.position.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
	private static final String END_COMMAND = "end";

	private final ChessBoard chessBoard;
	private final Turn turn;
	private ChessMenu chessMenu;

	public ChessGame() {
		this.chessBoard = new ChessBoard(BoardFactory.createBoard());
		this.turn = new Turn();
	}

	public void play() {
		OutputView.printRule();
		decideMenu();
		chessMenu.validateStart();
		while (chessMenu.isNotEnd()) {
			proceed();
			OutputView.printBoard(chessBoard);
			decideMenu();
		}
	}

	private void decideMenu() {
		if (isDieKing()) {
			OutputView.printEndGame();
			chessMenu = new ChessMenu(END_COMMAND);
			return;
		}
		try {
			chessMenu = new ChessMenu(InputView.inputMenu());
		} catch (IllegalArgumentException | UnsupportedOperationException e) {
			OutputView.printErrorMessage(e);
			decideMenu();
		}
	}

	private void proceed() {
		if (chessMenu.isMove()) {
			Position startPosition = chessMenu.createStartPosition();
			Position targetPosition = chessMenu.createTargetPosition();
			turn.validateTurn(chessBoard.findByPosition(startPosition));
			chessBoard.move(startPosition, targetPosition);
			turn.changeTurn();
		}

		if (chessMenu.isStatus()) {
			Status status = new Status(chessBoard.getBoard());
			Result result = status.getResult();
			OutputView.printResult(result);
		}
	}

	private boolean isDieKing() {
		return chessBoard.isDieKing(Team.BLACK) || chessBoard.isDieKing(Team.WHITE);
	}

}
