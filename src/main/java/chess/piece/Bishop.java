package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class Bishop extends AbstractPiece {

    public Bishop(Color color, Position position) {
        super("B", color, position);
    }

    @Override
    public Bishop moveTo(Position destination) {
        return new Bishop(getColor(), destination);
    }

    @Override
    public boolean isMovableTo(Position destination, final Map<Position, Piece> positions) {
        if (canMoveLeftUp(getPosition(), destination, positions)) {
            return true;
        }
        if (canMoveLeftDown(getPosition(), destination, positions)) {
            return true;
        }
        if (canMoveRightUp(getPosition(), destination, positions)) {
            return true;
        }
        return canMoveRightDown(getPosition(), destination, positions);
    }

    private boolean canMoveLeftUp(Position position, Position destination, Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveLeftUp()) {
            target = target.moveLeftUp();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveLeftDown(Position position, Position destination, final Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveLeftDown()) {
            target = target.moveLeftDown();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveRightUp(Position position, Position destination, final Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveRightUp()) {
            target = target.moveRightUp();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveRightDown(Position position, Position destination, final Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveRightDown()) {
            target = target.moveRightDown();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }
}
