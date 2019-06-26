package chess.model.piece;

import chess.model.Direction;
import chess.model.PlayerType;
import chess.model.Point;

public class Knight extends Piece {
    private static double KNIGHT_SCORE = 2.5;

    public Knight(PlayerType team, Point point) {
        super(team, point, KNIGHT_SCORE);
    }

    @Override
    public Piece createBlack(Point point) {
        return new Knight(PlayerType.BLACK, point);
    }

    @Override
    public Piece createWhite(Point point) {
        return new Knight(PlayerType.WHITE, point);
    }

    @Override
    public boolean canMove(Direction direction, Point destination) {
        double distance = point.calculateDistance(destination);
        return distance == Math.sqrt(5) && Direction.knightDirection().contains(direction);
    }

    @Override
    public String toString() {
        return "knight";
    }
}
