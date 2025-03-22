package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(Position position, Board board, Color color) {
        super(position, board, color);
    }

    @Override
    protected Set<Position> getMovablePositions() {
        Set<Position> movablePositions = new HashSet<>();
        Arrays.stream(Movement.values())
                .filter(Movement::isCrossOneStep)
                .forEach(movement -> {
                    Position currPosition = getPosition();
                    while(currPosition.canMove(movement)) {
                        currPosition = currPosition.move(movement);
                        if (!board.existSameTeam(currPosition, getColor())) {
                            movablePositions.add(currPosition);
                        }
                        if (board.existPiece(currPosition)) {
                            break;
                        }
                    }
                });
        return movablePositions;
    }

    @Override
    public String toString() {
        return "BI";
    }
}
