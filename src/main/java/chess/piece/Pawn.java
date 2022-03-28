package chess.piece;

import chess.position.Direction;
import chess.position.Position;

import java.util.List;

import static chess.utils.PossiblePositionChecker.isMovableCoordinates;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public List<Position> computeBetweenTwoPosition(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            if (color == Color.WHITE) {
                return List.of(source.findPossiblePosition(-1, 0));
            }
            if (color == Color.BLACK) {
                return List.of(source.findPossiblePosition(1, 0));
            }
        }
        return List.of();
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

    public boolean isDiagonal(Position source, Position target) {
        if (color == Color.WHITE) {
            return isMovableCoordinates(Direction.pawnWhiteDiagonal(), source, target);
        }
        return isMovableCoordinates(Direction.pawnBlackDiagonal(), source, target);
    }

    private boolean isMovableWhenWhite(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            return isMovableCoordinates(Direction.pawnWhiteFirstTurn(), source, target);
        }
        return isMovableCoordinates(Direction.pawnWhiteTurn(), source, target);
    }

    private boolean isMovableWhenBlack(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            return isMovableCoordinates(Direction.pawnBlackFirstTurn(), source, target);
        }
        return isMovableCoordinates(Direction.pawnBlackTurn(), source, target);
    }
}
