package chess;

import chess.game.ChessGame;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ChessGame chessGame = new ChessGame(new InputView(scanner), new OutputView());
            chessGame.start();
        }
    }
}
