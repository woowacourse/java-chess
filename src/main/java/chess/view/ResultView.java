package chess.view;

import static chess.view.command.StartCommand.END;

public class ResultView {

    public void printGameEnd() {
        System.out.printf(END.getMessage());
    }

    public void printBoard() {

    }
}
