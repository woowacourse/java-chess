package chess.piece;

import chess.position.Direction;
import chess.position.Position;

import java.util.List;

import static chess.utils.PossiblePositionChecker.isMovablePositions;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        if (color == Color.WHITE) {
            return isMovableWhenWhite(source, target);
        }
        if (color == Color.BLACK) {
            return isMovableWhenBlack(source, target);
        }
        return false;
    }

    private boolean isMovableWhenWhite(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            return isMovablePositions(Direction.pawnWhiteFirstTurn(), source, target);
        }
        return isMovablePositions(Direction.pawnWhiteTurn(), source, target);
    }

    private boolean isMovableWhenBlack(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            return isMovablePositions(Direction.pawnBlackFirstTurn(), source, target);
        }
        return isMovablePositions(Direction.pawnBlackTurn(), source, target);
    }

    public boolean isMovableDiagonal(Position source, Position target) {
        if (color == Color.WHITE) {
            return isMovablePositions(Direction.pawnWhiteDiagonal(), source, target);
        }
        return isMovablePositions(Direction.pawnBlackDiagonal(), source, target);
    }

    @Override
    public List<Position> computeBetweenTwoPositionByLine(Position source, Position target) {
        return List.of();
    }
}
