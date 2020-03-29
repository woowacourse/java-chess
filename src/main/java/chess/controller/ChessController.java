package chess.controller;

import chess.domain.Result;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessboard.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;
	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private static final String BLANK = " ";
	private static final String STATUS = "status";

	public static void run() {
		String userInputToStart = InputView.inputGameState();
		if (InputView.START_COMMAND.equalsIgnoreCase(userInputToStart)) {
			chessStart();
		}
		OutputView.printGameEndMessage();
	}

	private static void chessStart() {
		ChessBoard chessBoard = new ChessBoard();
		OutputView.printChessBoard(chessBoard);

		while (chessBoard.isSurviveKings()) {
			gameRun(chessBoard);
		}

		String inputStatus = InputView.inputStatus();
		if (STATUS.equalsIgnoreCase(inputStatus)) {
			Result result = chessBoard.calculateTeamScore();
			OutputView.printGameResult(result.getBlackTeamScore(), result.getWhiteTeamScore());
		}
	}

	private static void gameRun(ChessBoard chessBoard) {
		try {
			String moveCommand = InputView.inputMoveCommand();
			List<String> positionsOfInput = extractMovePosition(moveCommand);

			Position source = Position.of(positionsOfInput.get(FILE_INDEX));
			Position target = Position.of(positionsOfInput.get(RANK_INDEX));
			chessBoard.movePiece(source, target);

			OutputView.printChessBoard(chessBoard);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			OutputView.printChessBoard(chessBoard);
			gameRun(chessBoard);
		}
	}

	private static List<String> extractMovePosition(String moveCommand) {
		String[] commands = moveCommand.split(BLANK);
		return Arrays.asList(commands[SOURCE_INDEX], commands[TARGET_INDEX]);
	}
}
