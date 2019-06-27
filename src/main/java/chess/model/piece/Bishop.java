package chess.model.piece;

import chess.model.Direction;
import chess.model.PlayerType;
import chess.model.Point;

public class Bishop extends Piece {
    private static double BISHOP_SCORE = 3;

    public Bishop(PlayerType team, Point point) {
        super(team, point, BISHOP_SCORE);
    }


    @Override
    public boolean canMove(Direction direction, Point destination) {
        return Direction.bishopDirection().contains(direction);
    }

    @Override
    public String toString() {
        return "bishop";
    }
}
