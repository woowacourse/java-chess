package chess.controller.command;

import chess.domain.Chess;

public class ExitController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        chess.terminate();
        
        return chess;
    }
}
