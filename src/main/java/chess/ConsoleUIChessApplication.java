package chess;

import chess.controller.ConsoleChessGame;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        ConsoleChessGame consoleChessGame = new ConsoleChessGame();
        consoleChessGame.run();
    }
}
