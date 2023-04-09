package chess;

import chess.controller.ChessGameController;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (final Scanner scanner = new Scanner(System.in)) {
            final ChessGameController chessGameController = new ChessGameController(new InputView(scanner), new OutputView(), new ChessGameService());
            chessGameController.run();
        }
    }
}
