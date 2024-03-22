package chess;

import chess.controller.ChessGame;
import chess.view.InputView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ChessGame chessGame = new ChessGame(new InputView(scanner));
            chessGame.start();
        }
    }
}
