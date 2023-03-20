package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public class Pawn extends Piece {
    
    
    public static final List<Integer> FIRST_MOVABLE_DISTANCES = List.of(1, 2, 4);
    public static final List<Integer> DEFAULT_MOVABLE_DISTANCE = List.of(1, 2);
    
    private Pawn(final Color color) {
        super(color, PieceType.PAWN, getPawnDirectionBy(color));
    }
    
    private static List<Direction> getPawnDirectionBy(Color color) {
        if (color == Color.WHITE) {
            return List.of(Direction.N, Direction.NE, Direction.NW);
        }
        if (color == Color.BLACK) {
            return List.of(Direction.S, Direction.SE, Direction.SW);
        }
        return List.of();
    }
    
    public static Pawn create(Color color) {
        return new Pawn(color);
    }
    
    @Override
    public void canMove(final Position start, final Position end) {
        Direction direction = start.calculateDirection(end);
        checkDirection(direction);
        Rank rank = start.getRank();
        if (rank == Rank.TWO || rank == Rank.SEVEN) {
            checkDistance(start, end, FIRST_MOVABLE_DISTANCES);
            return;
        }
        checkDistance(start, end, DEFAULT_MOVABLE_DISTANCE);
    }
}
