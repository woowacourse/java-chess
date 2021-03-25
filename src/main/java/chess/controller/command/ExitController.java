package chess.controller.command;

import chess.domain.Chess;
import chess.view.OutputView;

public class ExitController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        chess.terminate();
        OutputView.printExit();
        return chess;
    }
}
