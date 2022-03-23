package chess;

import chess.view.InputView;

public class Application {
    public static void main(String[] args) {
        Command command = InputView.requestCommand();
        if (command.isStart()) {

        }
    }
}
