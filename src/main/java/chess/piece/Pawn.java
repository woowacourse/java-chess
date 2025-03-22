package chess.piece;

import chess.Board;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    boolean isFirstMove = true;

    public Pawn(Position position, Board board, Color color) {
        super(position, board, color);
    }

    @Override
    public void move(Position position) {
        super.move(position);
        isFirstMove = false;
    }

    @Override
    public Set<Position> getMovablePositions() {
        Set<Position> movablePositions = new HashSet<>();
        Movement movement = getMovableMovement();
        Position currPosition = getPosition();
        for (int i = 0; i < computeMovableCount(); i++) {
            if (!currPosition.canMove(movement)) {
                continue;
            }
            currPosition = currPosition.move(movement);
            if (currPosition.canMove(Movement.LEFT) && i == 0 &&
                    board.existSameTeam(currPosition.move(Movement.LEFT), getColor().opposite())
            ) {
                movablePositions.add(currPosition.move(Movement.LEFT));
            }
            if (currPosition.canMove(Movement.RIGHT) && i == 0 &&
                    board.existSameTeam(currPosition.move(Movement.LEFT), getColor().opposite())
            ) {
                movablePositions.add(currPosition.move(Movement.LEFT));
            }
            Color otherTeam = getColor().opposite();
            if (board.existSameTeam(currPosition, otherTeam)) {
                movablePositions.add(currPosition);
                return movablePositions;
            }
            if (board.existSameTeam(currPosition, getColor())) {
                return movablePositions;
            }
            movablePositions.add(currPosition);
        }
        return movablePositions;
    }

    private int computeMovableCount() {
        if (isFirstMove) {
            return 2;
        }
        return 1;
    }

    private Movement getMovableMovement() {
        if (getColor().isBlack()) {
            return Movement.DOWN;
        }
        return Movement.UP;
    }

    @Override
    public String toString() {
        return "PA";
    }
}
