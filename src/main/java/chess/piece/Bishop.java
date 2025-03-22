package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Movement> MOVEMENTS = List.of(
            Movement.LEFT_UP, Movement.LEFT_DOWN, Movement.RIGHT_UP, Movement.RIGHT_DOWN
    );

    public Bishop(Color color, Position position) {
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
            return "b";
        }
        return "B";
    }
}
