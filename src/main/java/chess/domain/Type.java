package chess.domain;

import java.util.List;

public enum Type {
    WHITE_PAWN(1.0, Direction.whitePawnDirection()),
    BLACK_PAWN(1.0, Direction.blackPawnDirection()),
    ROOK(5.0, Direction.linearDirection()),
    KNIGHT(2.5, Direction.knightDirection()),
    BISHOP(3.0, Direction.diagonalDirection()),
    QUEEN(9.0, Direction.everyDirection()),
    KING(0.0, Direction.everyDirection()),
    BLANK(0.0, Direction.blankDirection());

    private double score;
    private List<Direction> directions;

    Type(double score, List<Direction> directions) {
        this.score = score;
        this.directions = directions;
    }

    public double getScore() {
        return score;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
