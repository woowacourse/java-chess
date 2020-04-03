package chess.domain.piece;

import chess.domain.board.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public class Knight extends Piece {
    private static final String NAME = "n";
    private static final double SCORE = 2.5;
    private static final int MOVABLE_DISTANCE_SUM = 3;

    public Knight(PieceColor pieceColor, Position position) {
        super(NAME, SCORE, pieceColor, position);
    }

    @Override
    public List<Position> getPathTo(Position targetPosition) {
        List<Position> path = new ArrayList<>();

        validateNull(targetPosition);
        validateEqualPosition(targetPosition);
        validateDistance(targetPosition);

        path.add(targetPosition);
        return path;
    }

    @Override
    protected void validateDistance(Position targetPosition) {
        int xPointGap = this.position.getXPointGap(targetPosition);
        int yPointGap = this.position.getYPointGap(targetPosition);
        int gapSum = Math.abs(xPointGap) + Math.abs(yPointGap);

        if (gapSum != MOVABLE_DISTANCE_SUM) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 위치입니다.");
        }
    }
}
