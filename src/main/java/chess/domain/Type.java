package chess.domain;

import chess.domain.pieces.*;

import java.util.List;
import java.util.function.Function;

public enum Type {
    WHITE_PAWN(1.0, Direction.whitePawnDirection(), Pawn::new),
    BLACK_PAWN(1.0, Direction.blackPawnDirection(), Pawn::new),
    ROOK(5.0, Direction.linearDirection(), Rook::new),
    KNIGHT(2.5, Direction.knightDirection(), Knight::new),
    BISHOP(3.0, Direction.diagonalDirection(), Bishop::new),
    QUEEN(9.0, Direction.everyDirection(), Queen::new),
    KING(0.0, Direction.everyDirection(), King::new),
    BLANK(0.0, Direction.blankDirection(), (t) -> new Blank());

    private double score;
    private List<Direction> directions;
    private Function<Team, Piece> constructor;


    Type(double score, List<Direction> directions, Function<Team, Piece> constructor) {
        this.score = score;
        this.directions = directions;
        this.constructor = constructor;
    }

    public double getScore() {
        return score;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public Piece create(Team team) {
        return this.constructor.apply(team);
    }
}
