package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;

import java.util.List;

import static chess.util.NullValidator.validateNull;

public class Queen extends Piece {
    private static final String NAME = "q";
    private static final double SCORE = 9;

    public Queen(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position);
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

        if (this.position.isDifferentXPoint(targetPosition) && this.position.isDifferentYPoint(targetPosition)
                && (Math.abs(xPointGap) != Math.abs(yPointGap))) {
            throw new IllegalArgumentException("퀸이 이동할 수 없는 위치입니다.");
        }
    }
}
