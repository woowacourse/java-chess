package chess;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        String command = "";
        while (!command.equals("end")) {
            List<String> inputCommand = Arrays.asList(InputView.inputStartOrEndGame());
            command = inputCommand.get(0);
            if (command.equals("start")) {
                BoardDto boardDto = chessController.startGame();
                OutputView.announce(boardDto);
                continue;
            }
            if (command.equals("move")) {
                BoardDto boardDto = chessController.move(inputCommand.get(1), inputCommand.get(2));
                OutputView.announce(boardDto);
            }
            if (command.equals("status")) {
                chessController.score();
            }
        }
    }
}
