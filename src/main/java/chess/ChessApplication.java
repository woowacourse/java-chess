package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(
                new InputView(new Scanner(System.in)),
                new OutputView(),
                ChessGame.from(ArrayList::new));
        chessController.run();
    }
}
