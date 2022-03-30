package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Map;

import static chess.utils.PossiblePositionChecker.isMovablePositions;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
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
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }

    @Override
    public boolean isDotPiece() {
        return true;
    }
}
