package chess.piece.movementcondition;

import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;

public interface MovementCondition {

    boolean isPossibleMovement(Position from, Position to, Map<Position, Piece> board);
}
