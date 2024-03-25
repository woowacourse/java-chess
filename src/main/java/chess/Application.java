package chess;

import chess.game.ChessGame;
import chess.view.input.InputView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ChessGame chessGame = new ChessGame(new InputView(scanner));
            chessGame.start();
        }
    }
}
