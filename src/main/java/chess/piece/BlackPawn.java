package chess.piece;

import chess.ChessBoard;
import chess.Color;
import chess.Movement;
import chess.Position;
import chess.Row;
import java.util.Set;

public class BlackPawn extends Pawn {

    private static final Row INITINAL_ROW = Row.SEVEN;

    public BlackPawn(Position position) {
        super(Color.BLACK, position);
    }

    @Override
    protected boolean isAttack(ChessBoard chessBoard, Position to) {
        return chessBoard.isColorInPosition(Color.WHITE, to);
    }

    @Override
    protected boolean isFirstMovement() {
        return position.isRow(INITINAL_ROW);
    }

    @Override
    protected Set<Movement> attackMovements() {
        return Set.of(Movement.LEFT_DOWN, Movement.RIGHT_DOWN);
    }

    @Override
    protected Movement movement() {
        return Movement.DOWN;
    }

    @Override
    public String toString() {
        return "p";
    }
}
