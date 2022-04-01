package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final int COMMAND_INDEX = 0;

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        while (!chessController.isEnd()) {
            List<String> inputCommand = Arrays.asList(InputView.inputStartOrEndGame());
            ChessCommand command = ChessCommand.findCommand(inputCommand.get(COMMAND_INDEX));
            command.accept(chessController, inputCommand);
        }
    }
}
