package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;

import java.util.List;

import static chess.util.NullValidator.validateNull;

public class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
    }

    @Override
    public List<Position> getPathTo(Position targetPosition) {
        validateNull(targetPosition);
        validateEqualPosition(targetPosition);
        validateDistance(targetPosition);
        Direction direction = getDirection(targetPosition);

        return createPath(targetPosition, direction);
    }

    @Override
    protected void validateDistance(Position targetPosition) {
        if (position.isDifferentXPoint(targetPosition) && position.isDifferentYPoint(targetPosition)) {
            throw new IllegalArgumentException("룩이 이동할 수 없는 위치입니다.");
        }
    }
}
