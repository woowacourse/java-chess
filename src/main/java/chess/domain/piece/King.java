package chess.domain.piece;

import chess.domain.board.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public class King extends Piece {
    private static final String NAME = "k";
    private static final int MAX_DISTANCE = 1;

    public King(PieceColor pieceColor, Position position) {
        super(NAME, pieceColor, position);
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

        if (MAX_DISTANCE < Math.abs(xPointGap) || MAX_DISTANCE < Math.abs(yPointGap)) {
            throw new IllegalArgumentException("킹이 이동할 수 없는 위치입니다.");
        }
    }
}
