package controller;

import domain.chessgame.ChessGame;
import domain.position.Position;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";

    public void run() {
        try {
            if (!InputView.command().equals(START_COMMAND)) {
                throw new IllegalArgumentException();
            }
            startChessGame();
        } catch (Exception e) {
            run();
        }
    }

    private void startChessGame() {
        ChessGame chessGame = new ChessGame();
        while (chessGame.isNotEnd()) {
            OutputView.printBoard(chessGame.getBoard());
            List<String> command = InputView.moveCommand();
            if (command.get(0).equals(END_COMMAND)) {
                return;
            }
            if (command.get(0).equals(STATUS_COMMAND)) {
                OutputView.printScore(chessGame.getBoard());
            }
            if (command.get(0).equals(MOVE_COMMAND)) {
                chessGame.move(new Position(command.get(1)), new Position(command.get(2)));
            }
        }
    }

}
