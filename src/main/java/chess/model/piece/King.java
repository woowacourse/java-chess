package chess.model.piece;

import chess.model.Direction;
import chess.model.PlayerType;
import chess.model.Point;

public class King extends Piece {
    private static final double KING_SCORE = 0;
    private static final double KING_MAX_DISTANCE = Math.sqrt(2);

    public King(PlayerType team, Point point) {
        super(team, point, KING_SCORE);
    }

    @Override
    public boolean canMove(Direction direction, Point destination) {
        double distance = point.calculateDistance(destination);
        return distance <= KING_MAX_DISTANCE && Direction.allDirection().contains(direction);
    }

    @Override
    public Piece createBlack(Point point) {
        return new King(PlayerType.BLACK, point);
    }

    @Override
    public Piece createWhite(Point point) {
        return new King(PlayerType.WHITE, point);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public String toString() {
        return "king";
    }
}
