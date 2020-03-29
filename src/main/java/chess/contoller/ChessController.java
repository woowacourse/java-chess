package chess.contoller;

import chess.contoller.command.UserCommand;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.dto.ChessBoardAssembler;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
	public void run() {
		OutputView.printInitMessage();
		ChessGame chessGame = new ChessGame(ChessBoardFactory.create());
		while (chessGame.isNotEnd()) {
			chessGame = runGame(chessGame);
		}
	}

	private ChessGame runGame(ChessGame chessGame) {
		try {
			UserCommand userCommand = UserCommand.of(InputView.inputCommand());
			chessGame = executeCommand(chessGame, userCommand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return chessGame;
	}

	private ChessGame executeCommand(ChessGame chessGame, UserCommand userCommand) {
		switch (userCommand.getCommand()) {
			case START:
				chessGame = new ChessGame(ChessBoardFactory.create());
				OutputView.printBoard(ChessBoardAssembler.create(chessGame.getChessBoard()));
				break;
			case MOVE:
				userCommand.validateOptionCount();
				chessGame = chessGame.move(new Position(userCommand.pollOption()), new Position(userCommand.pollOption()));
				OutputView.printBoard(ChessBoardAssembler.create(chessGame.getChessBoard()));
				break;
			case STATUS:
				ChessStatus chessStatus = chessGame.createStatus();
				OutputView.printScore(chessStatus.calculateScore(Side.BLACK), chessStatus.calculateScore(Side.WHITE));
			case END:
				chessGame = chessGame.end();
		}
		return chessGame;
	}
}
