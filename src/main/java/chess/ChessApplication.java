package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {
        final InputView inputView = new InputView(new Scanner(System.in));
        final OutputView outputView = new OutputView();
        final ChessController chessController = new ChessController(inputView, outputView);

        chessController.run();
    }
}
