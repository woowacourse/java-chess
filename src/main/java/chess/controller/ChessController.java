package chess.controller;

import chess.domain.ChessGame;
import chess.domain.OperationType;
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

		if (!operationType.isStart()) {
			//exception
		}
		OutputView.printBoard(chessGame);
		while (true) {
			operation = inputOperation();
			operations = Arrays.asList(operation.split(" "));
			operationType = OperationType.of(operations.get(0));

			if (operationType.isEnd()) {
				return;
			}
			if (operationType.isMove()) {
				//추가입력
				chessGame.move(Board.of(operations.get(1)), Board.of(operations.get(2)));

				OutputView.printBoard(chessGame);
			}
			//isStatus
		}
	}
}
