package chess.piece;

import chess.Color;
import chess.Position;
import java.util.Map;

public class Rook extends AbstractPiece {

    public Rook(Color color, Position position) {
        super("R", color, position);
    }

    @Override
    public Rook moveTo(Position destination) {
        return new Rook(getColor(), destination);
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
        return canMoveRight(getPosition(), destination, positions);
    }

    private boolean canMoveUp(Position position, Position destination, final Map<Position, Piece> positions) {
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

    private boolean canMoveDown(Position position, Position destination, final Map<Position, Piece> positions) {
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

    private boolean canMoveLeft(Position position, Position destination, final Map<Position, Piece> positions) {
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

    private boolean canMoveRight(Position position, Position destination, final Map<Position, Piece> positions) {
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
}
