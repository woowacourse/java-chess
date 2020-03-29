package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.OperationType;
import chess.domain.game.Operations;
import chess.view.OutputView;

import static chess.view.InputView.inputOperation;

public class ChessController {
	public void run() {
		OutputView.printOperationsFormat();
		if (!beforeStart()) {
			return;
		}
		execute();
		OutputView.printFinish();
	}

	private void execute() {
		ChessGame chessGame = new ChessGame();
		OutputView.printBoard(new Board(chessGame.getPieces()));

		while (executeOperation(chessGame)) {
			OutputView.printBoard(new Board(chessGame.getPieces()));
			OutputView.printScore(chessGame.calculateScore());
		}
		if(chessGame.isKingDead()) {
			OutputView.printFinishByKingDead(chessGame.getAliveKingColor());
		}
	}

	private boolean beforeStart() {
		OperationType operationType = new Operations(inputOperation()).getOperationType();

		operationType.validateFirstOperations();
		if (operationType.isEnd()) {
			OutputView.printFinish();
			return false;
		}
		return true;
	}

	private boolean executeOperation(ChessGame chessGame) {
		Operations operations = new Operations(inputOperation());
		OperationType operationType = operations.getOperationType();

		return (operationType.runOperate(chessGame,operations) && !chessGame.isKingDead());
	}
}