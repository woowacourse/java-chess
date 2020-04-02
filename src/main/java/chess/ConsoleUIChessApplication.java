package chess;

import chess.contoller.ConsoleChessController;

public class ConsoleUIChessApplication {
	public static void main(String[] args) {
		ConsoleChessController consoleChessController = new ConsoleChessController();
		consoleChessController.run();
	}
}
