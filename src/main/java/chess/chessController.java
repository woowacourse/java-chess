package chess;

import chess.domain.Command;
import chess.view.InputView;

public class chessController {
    public static void main(String[] args) {
        Command.of(InputView.requestCommand()).run();
    }
}
