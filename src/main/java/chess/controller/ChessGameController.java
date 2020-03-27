package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
	private ChessGame chessGame;

	public ChessGameController(ChessGame chessGame) {
		this.chessGame = chessGame;
	}

	public void command(String input) {
		String[] tokens = input.split(" ");
		switch (tokens[0]) {
			case "start":
				chessGame.start();
				break;
			case "end":
				chessGame.end();
				break;
			case "move":
				chessGame.move(Position.from(tokens[1]), Position.from(tokens[2]));
				break;
			case "status":
				OutputView.printStatus(chessGame.status());
				break;
			default:
				throw new IllegalArgumentException("존재하지 않는 커맨드 입니다.");
		}
	}

	public void run() {
		OutputView.printGameStart();
		while (!chessGame.isFinished()) {
			command(InputView.inputCommand());
			OutputView.printBoard(chessGame.board());
		}
	}
}
