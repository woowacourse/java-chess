package chess;

import chess.config.ChessConfigure;
import chess.controller.ChessController;

public class ChessApplication {
    public static void main(String[] args) {

        final ChessConfigure chessConfigure = new ChessConfigure();

        new ChessController(chessConfigure.boardQueryService(),
                            chessConfigure.boardCommandService()).run();
    }
}
