package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.OperationType;
import chess.domain.board.Board;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

import static chess.view.InputView.inputOperation;

public class ChessController {

	public void run() {
		ChessGame chessGame = new ChessGame();

		OutputView.printOperationsFormat();
		String operation = inputOperation();
		List<String> operations = Arrays.asList(operation.split(" "));
		OperationType operationType = OperationType.of(operations.get(0));

		if (!operationType.isStart() && !operationType.isEnd()) {
			throw new UnsupportedOperationException("잘못된 명령입니다.");
		}

		if (operationType.isEnd()) {
			OutputView.printFinish();
			return;
		}

		OutputView.printBoard(chessGame);

		while (true) {
			operation = inputOperation();
			operations = Arrays.asList(operation.split(" "));
			operationType = OperationType.of(operations.get(0));

			if (operationType.isEnd()) {
				OutputView.printScore(chessGame.calculateScore());
				OutputView.printFinish();
				return;
			}

			if (operationType.isMove()) {
				if (operateMove(chessGame, operations)){
					OutputView.printScore(chessGame.calculateScore());
					return;
				}
			}

			if (operationType.isStatus()) {
				OutputView.printScore(chessGame.calculateScore());
			}

			if (operationType.isStart()) {
				throw new UnsupportedOperationException("잘못된 명령입니다.");
			}
			OutputView.printBoard(chessGame);
		}
	}

	private boolean operateMove(ChessGame chessGame, List<String> operations) {
		chessGame.move(Board.of(operations.get(1)), Board.of(operations.get(2)));
		if (chessGame.isKingDead()) {
			OutputView.printFinishByKingDead(chessGame.getAliveKingColor());
			return true;
		}
		return false;
	}
}
