package chess.domain.board;

import chess.domain.UserInterface;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

class MoveExceptionHandler {
    static Board handle(Movable movable, MovingFlow movingFlow, UserInterface userInterface, Board board) {
        try {
            Position from = movingFlow.getFrom();
            Position to = movingFlow.getTo();
            return movable.move(from, to, board);
        } catch (IllegalArgumentException e) {
            movingFlow = userInterface.inputMovingFlow();
            return handle(movable, movingFlow, userInterface, board);
        }
    }
}
