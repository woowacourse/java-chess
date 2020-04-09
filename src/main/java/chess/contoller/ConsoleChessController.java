package chess.contoller;

import chess.contoller.command.UserCommand;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.dto.ChessBoardAssembler;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessController {
	public void run() {
		OutputView.printInitMessage();
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		while (chessGame.isNotEnd()) {
			runGame(chessGame);
		}
	}

	private void runGame(ChessGame chessGame) {
		try {
			UserCommand userCommand = UserCommand.of(InputView.inputCommand());
			executeCommand(chessGame, userCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void executeCommand(ChessGame chessGame, UserCommand userCommand) {
		switch (userCommand.getCommand()) {
			case START:
				chessGame = new ChessGame(ChessBoardFactory.create());
				OutputView.printBoard(ChessBoardAssembler.create(chessGame.getChessBoard()));
				break;
			case MOVE:
				userCommand.validateOptionCount();
				chessGame.move(Position.of(userCommand.pollOption()), Position.of(userCommand.pollOption()));
				OutputView.printBoard(ChessBoardAssembler.create(chessGame.getChessBoard()));
				break;
			case STATUS:
				ChessStatus chessStatus = chessGame.createStatus();
				OutputView.printScore(chessStatus.calculateScore(Side.BLACK), chessStatus.calculateScore(Side.WHITE));
				break;
			case END:
				chessGame.end();
				break;
		}
	}
}
