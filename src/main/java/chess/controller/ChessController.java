package chess.controller;

import static chess.util.StringUtil.*;
import static chess.view.ConsoleInputView.*;
import static chess.view.ConsoleOutputView.*;

import java.util.List;
import java.util.Objects;

import chess.domain.ChessCommand;
import chess.domain.ChessGame;
import chess.view.ConsoleOutputView;

public class ChessController {

	private static final int CHESS_COMMAND_INDEX = 0;

	private final ChessGame chessGame;

	public ChessController(ChessGame chessGame) {
		Objects.requireNonNull(chessGame, "체스 게임이 null입니다.");
		this.chessGame = chessGame;
	}

	public void run() {
		ChessCommand chessCommand;

		do {
			ConsoleOutputView.printChessBoard(chessGame.getRenderedChessBoard());
			List<String> chessCommandArguments = splitChessCommand(inputChessCommand());

			chessCommand = ChessCommand.of(chessCommandArguments.remove(CHESS_COMMAND_INDEX));
			playChessGameBy(chessCommand, chessCommandArguments);
		} while (!isEndState());
	}

	private void playChessGameBy(ChessCommand chessCommand, List<String> chessCommandArguments) {
		if (chessCommand.isStartChessCommand()) {
			throw new IllegalArgumentException("start 명령어은 최초 시작 때만 사용 가능합니다.");
		}
		chessCommand.playChessGame(chessGame, chessCommandArguments);
	}

	private boolean isEndState() {
		if (!chessGame.isEndState()) {
			return false;
		}
		return checkKingCaught();
	}

	private boolean checkKingCaught() {
		if (chessGame.isKingCaught()) {
			printKingCaught(chessGame.getCurrentTurnPieceColor());
		}
		return true;
	}

}
