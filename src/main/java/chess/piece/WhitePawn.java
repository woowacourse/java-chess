package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.Set;

public class WhitePawn extends Pawn {

    private static final Row INITINAL_ROW = Row.TWO;

    public WhitePawn(Position position) {
        super(Color.WHITE, position);
    }

    @Override
    protected boolean isAttack(ChessBoard chessBoard, Position to) {
        return chessBoard.isColorInPosition(Color.BLACK, to);
    }

    @Override
    protected boolean isFirstMovement() {
        return position.isRow(INITINAL_ROW);
    }

    @Override
    protected Set<Movement> attackMovements() {
        return Set.of(Movement.LEFT_UP, Movement.RIGHT_UP);
    }

    @Override
    protected Movement movement() {
        return Movement.UP;
    }

    @Override
    public String toString() {
        return "P";
    }
}
