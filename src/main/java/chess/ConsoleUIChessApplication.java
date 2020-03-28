package chess;

import static chess.view.ConsoleInputView.*;
import static chess.view.ConsoleOutputView.*;

import java.util.Objects;

import chess.controller.ChessController;
import chess.domain.ChessCommand;
import chess.domain.ChessGame;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;

public class ConsoleUIChessApplication {

	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());
		ChessGame chessGame = new ChessGame(chessBoard);
		ChessController chessController = new ChessController(chessGame);

		printChessStart();
		if (isStartChessCommand()) {
			chessController.run();
		}
		printChessEnd();
	}

	private static boolean isStartChessCommand() {
		if (!ChessCommand.of(receiveChessCommand()).isStartChessCommand()) {
			throw new IllegalArgumentException("게임을 시작해야 입력 가능한 명령어입니다.");
		}
		return true;
	}

	private static String receiveChessCommand() {
		String inputChessCommand = inputChessCommand();

		Objects.requireNonNull(inputChessCommand, "명령어 입력이 null입니다.");
		return inputChessCommand.trim();
	}

}
