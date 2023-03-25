package chess;

import chess.controller.ChessController;
import chess.view.IOViewResolver;

import java.util.Scanner;

public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ChessController chessController = new ChessController(new IOViewResolver(scanner));
            chessController.process();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
