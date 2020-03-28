package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;

import java.util.List;

import static chess.util.NullValidator.validateNull;

public class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(PieceColor pieceColor, Position position) {
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
        int xPointGap = this.position.getXPointGap(targetPosition);
        int yPointGap = this.position.getYPointGap(targetPosition);

        if (Math.abs(xPointGap) != Math.abs(yPointGap)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
    }
}
