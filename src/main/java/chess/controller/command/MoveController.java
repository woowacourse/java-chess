package chess.controller.command;

import chess.domain.Chess;
import chess.domain.position.MovePosition;
import chess.view.OutputView;

public class MoveController implements CommandController {
    
    private final MovePosition movePosition;
    
    public MoveController(String input) {
        this(MovePosition.from(input));
    }
    
    public MoveController(MovePosition movePosition) {
        this.movePosition = movePosition;
    }
    
    @Override
    public Chess execute(Chess chess) {
        chess.checkGameIsRunning();
        chess.movePiece(movePosition);
        OutputView.printBoard(chess);
        return chess;
    }
}
