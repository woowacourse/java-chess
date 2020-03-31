package chess;

import static chess.view.ConsoleInputView.*;
import static chess.view.ConsoleOutputView.*;

import java.util.List;

import chess.controller.ChessController;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import chess.util.StringUtil;

public class ConsoleUIChessApplication {

	// TODO: 2020/03/30 chessGame 패키지부터 테스트 작성
	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		ChessGame chessGame = ChessGame.from(chessBoard);
		ChessController chessController = new ChessController(chessGame);

		printChessStart();
		if (isStartChessCommand()) {
			chessController.run();
		}
		printChessEnd();
	}

	private static boolean isStartChessCommand() {
		List<String> commandArguments = StringUtil.splitChessCommand(inputChessCommand());

		if (!ChessCommand.of(commandArguments).isStartChessCommand()) {
			throw new IllegalArgumentException("게임을 시작해야 입력 가능한 명령어입니다.");
		}
		return true;
	}

}
