package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.Set;

public class Rook extends Piece {

    private static final Set<Movement> MOVEMENTS = Set.of(
            Movement.UP, Movement.DOWN, Movement.LEFT, Movement.RIGHT
    );

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovable(ChessBoard chessBoard, Position to) {
        for (Movement movement : MOVEMENTS) {
            Position curr = position;
            while (!curr.equals(to)) {
                if (!curr.canMove(movement)) {
                    break;
                }
                curr = curr.move(movement);
                if (chessBoard.isExistPiece(curr)) {
                    break;
                }
            }
            if (curr.equals(to)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isColor(Color.BLACK)) {
            return "r";
        }
        return "R";
    }
}
