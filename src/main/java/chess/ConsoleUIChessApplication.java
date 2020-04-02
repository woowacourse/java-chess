package chess;

import chess.view.GameManager;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.runGame();
    }
}
