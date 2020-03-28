package chess.controller;

import chess.domain.position.PositionFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.OperationType;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

import static chess.view.InputView.inputOperation;

public class ChessController {
	private static final String INVALID_OPERATION_EXCEPTION_MESSAGE = "잘못된 명령입니다.";
	private static final int START_POSITION_INDEX = 1;
	private static final int DESTINATION_POSITION_INDEX = 2;
	private static final int OPERATION_TYPE_INDEX = 0;

	public void run() {
		ChessGame chessGame = new ChessGame();

		OutputView.printOperationsFormat();
		List<String> operations = inputOperation();
		OperationType operationType = OperationType.of(operations.get(OPERATION_TYPE_INDEX));

		validateFirstOperations(operationType);
		if (operationType.isEnd()) {
			OutputView.printFinish();
			return;
		}

		do {
			OutputView.printBoard(chessGame);
			operations = inputOperation();
			operationType = OperationType.of(operations.get(OPERATION_TYPE_INDEX));
		} while (operateByType(chessGame, operations, operationType));
	}

	private void validateFirstOperations(OperationType operationType) {
		if (!operationType.isStart() && !operationType.isEnd()) {
			throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
		}
	}

	private boolean operateByType(ChessGame chessGame, List<String> operations, OperationType operationType) {
		if (operationType.isEnd()) {
			OutputView.printScore(chessGame.calculateScore());
			OutputView.printFinish();
			return false;
		}

		if (operationType.isMove()) {
			chessGame.move(PositionFactory.of(operations.get(START_POSITION_INDEX)), PositionFactory.of(operations.get(DESTINATION_POSITION_INDEX)));
			return isMoveOperating(chessGame);
		}

		if (operationType.isStatus()) {
			OutputView.printScore(chessGame.calculateScore());
			return true;
		}

		if (operationType.isStart()) {
			throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
		}
		throw new UnsupportedOperationException(INVALID_OPERATION_EXCEPTION_MESSAGE);
	}

	private boolean isMoveOperating(ChessGame chessGame) {
		if (chessGame.isKingDead()) {
			OutputView.printFinishByKingDead(chessGame.getAliveKingColor());
			return false;
		}
		return true;
	}
}
