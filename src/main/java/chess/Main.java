package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        while (!chessController.isEnd()) {
            List<String> inputCommand = Arrays.asList(InputView.inputStartOrEndGame());
            ChessCommand command = ChessCommand.findCommand(inputCommand.get(0));
            command.accept(chessController, inputCommand);
        }
    }
}
