package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class Queen extends AbstractPiece {
    public Queen(Color color, Position position) {
        super("Q", color, position);
    }

    @Override
    public Queen moveTo(Position destination) {
        return new Queen(getColor(), destination);
    }

    @Override
    public boolean isMovableTo(Position destination, final Map<Position, Piece> positions) {
        if (canMoveUp(getPosition(), destination, positions)) {
            return true;
        }
        if (canMoveDown(getPosition(), destination, positions)) {
            return true;
        }
        if (canMoveLeft(getPosition(), destination, positions)) {
            return true;
        }
        if (canMoveRight(getPosition(), destination, positions)) {
            return true;
        }
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

    private boolean canMoveUp(Position position, Position destination, Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveUp()) {
            target = target.moveUp();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveDown(Position position, Position destination, Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveDown()) {
            target = target.moveDown();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveLeft(Position position, Position destination, Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveLeft()) {
            target = target.moveLeft();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
    }

    private boolean canMoveRight(Position position, Position destination, Map<Position, Piece> positions) {
        Position target = position;
        while (target.canMoveRight()) {
            target = target.moveRight();
            if (target.equals(destination)) {
                if (positions.containsKey(target)) {
                    System.out.println("상대팀 말을 잡았습니다.");
                }
                return true;
            }
        }
        return false;
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

    private boolean canMoveLeftDown(Position position, Position destination, Map<Position, Piece> positions) {
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

    private boolean canMoveRightUp(Position position, Position destination, Map<Position, Piece> positions) {
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

    private boolean canMoveRightDown(Position position, Position destination, Map<Position, Piece> positions) {
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
