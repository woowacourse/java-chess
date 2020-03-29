package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends MultipleStep {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.NORTH,
                    Direction.EAST,
                    Direction.SOUTH,
                    Direction.WEST
            )
    );

    public Rook(char representation, Team team, Position position) {
        super(representation, team, position, PieceType.ROOK);
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        return new Rook(representation, team, toPosition);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
