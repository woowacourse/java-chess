package chess.controller;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.OperationType;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

import static chess.view.InputView.inputOperation;

public class ChessController {

	private static final String DELIMITER = " ";

	public void run() {
		ChessGame chessGame = new ChessGame();

		OutputView.printOperationsFormat();
		List<String> operations = Arrays.asList(inputOperation().split(DELIMITER));
		OperationType operationType = OperationType.of(operations.get(0));

		validateFirstOperations(operationType);
		if (operationType.isEnd()) {
			OutputView.printFinish();
			return;
		}

		do {
			OutputView.printBoard(chessGame);
			operations = Arrays.asList(inputOperation().split(DELIMITER));
			operationType = OperationType.of(operations.get(0));
		} while (operateByType(chessGame, operations, operationType));
	}

	private void validateFirstOperations(OperationType operationType) {
		if (!operationType.isStart() && !operationType.isEnd()) {
			throw new UnsupportedOperationException("잘못된 명령입니다.");
		}
	}

	private boolean operateByType(ChessGame chessGame, List<String> operations, OperationType operationType) {
		if (operationType.isEnd()) {
			OutputView.printScore(chessGame.calculateScore());
			OutputView.printFinish();
			return false;
		}

		if (operationType.isMove()) {
			chessGame.move(Board.of(operations.get(1)), Board.of(operations.get(2)));
			return isMoveOperating(chessGame, operations);
		}

		if (operationType.isStatus()) {
			OutputView.printScore(chessGame.calculateScore());
			return true;
		}

		if (operationType.isStart()) {
			throw new UnsupportedOperationException("잘못된 명령입니다.");
		}
		return true;
	}

	private boolean isMoveOperating(ChessGame chessGame, List<String> operations) {
		if (chessGame.isKingDead()) {
			OutputView.printFinishByKingDead(chessGame.getAliveKingColor());
			return false;
		}
		return true;
	}
}
