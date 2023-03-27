package chess.domain.piece.role;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.piece.position.WayPoints;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class Queen extends Piece {

    public Queen(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        this.type = Type.QUEEN;
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!path.isStraight() && !path.isDiagonal()) {
            throw new IllegalArgumentException("퀸은 직선 혹은 대각선으로만 이동할 수 있습니다.");
        }
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        return new WayPoints(path.wayPoints());
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return 9;
    }
}
