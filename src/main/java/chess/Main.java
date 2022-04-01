package chess;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        while (!chessController.isEnd()) {
            List<String> inputCommand = Arrays.asList(InputView.inputStartOrEndGame());
            String command = inputCommand.get(0);
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
                ScoreDto scoreDto = chessController.score();
                OutputView.announceScoreDto(scoreDto);
            }
            if (command.equals("end")) {
                chessController.finishGame();
            }
        }
    }
}
