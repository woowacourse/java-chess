package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

import static chess.utils.PossiblePositionChecker.isMovablePositions;

public class Pawn extends Piece {

    private static final List<Direction> WHITE_FIRST_DIRECTIONS = Direction.pawnWhiteFirstTurn();
    private static final List<Direction> WHITE_DIRECTIONS = Direction.pawnWhiteTurn();
    private static final List<Direction> BLACK_FIRST_DIRECTIONS = Direction.pawnBlackFirstTurn();
    private static final List<Direction> BLACK_DIRECTIONS = Direction.pawnBlackTurn();
    private static final List<Direction> WHITE_DIAGONAL_DIRECTIONS = Direction.pawnWhiteDiagonal();
    private static final List<Direction> BLACK_DIAGONAL_DIRECTIONS = Direction.pawnBlackDiagonal();

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
            return isMovablePositions(WHITE_FIRST_DIRECTIONS, source, target);
        }
        return isMovablePositions(WHITE_DIRECTIONS, source, target);
    }

    private boolean isMovableWhenBlack(Position source, Position target) {
        if (source.isFirstPosition(color)) {
            return isMovablePositions(BLACK_FIRST_DIRECTIONS, source, target);
        }
        return isMovablePositions(BLACK_DIRECTIONS, source, target);
    }

    public boolean isMovableDiagonal(Position source, Position target) {
        if (color == Color.WHITE) {
            return isMovablePositions(WHITE_DIAGONAL_DIRECTIONS, source, target);
        }
        return isMovablePositions(BLACK_DIAGONAL_DIRECTIONS, source, target);
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
