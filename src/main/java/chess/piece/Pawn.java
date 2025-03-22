package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Set;

public abstract class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    protected boolean isMovable(ChessBoard chessBoard, Position to) {
        if (isAttack(chessBoard, to)) {
            for (Movement movement : attackMovements()) {
                Position curr = position;
                if (!curr.canMove(movement)) {
                    continue;
                }
                curr = curr.move(movement);
                if (curr.equals(to)) {
                    return true;
                }
            }
            return false;
        }
        if (chessBoard.isExistPiece(to)) {
            return false;
        }
        if (isFirstMovement()) {
            Position curr = position;
            for (int cnt = 0; cnt < 2; cnt++) {
                if (!curr.canMove(movement())) {
                    break;
                }
                curr = curr.move(movement());
                if (chessBoard.isExistPiece(curr)) {
                    break;
                }
                if (curr.equals(to)) {
                    return true;
                }
            }
            return curr.equals(to);
        }
        return position.canMove(movement()) &&
                position.move(movement()).equals(to);
    }

    abstract protected boolean isAttack(ChessBoard chessBoard, Position to);

    abstract protected boolean isFirstMovement();

    abstract protected Set<Movement> attackMovements();

    abstract protected Movement movement();
}
