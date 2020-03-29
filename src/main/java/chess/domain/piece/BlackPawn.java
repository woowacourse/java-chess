package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackPawn extends Pawn {
    private static final List<Direction> DIRECTIONS = new ArrayList<>(
            Arrays.asList(
                    Direction.SOUTH,
                    Direction.SOUTH_EAST,
                    Direction.SOUTH_WEST
            )
    );

    public BlackPawn(char representation, Team team, Position position) {
        super(representation, team, position, PieceType.BLACK_PAWN);
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        return new BlackPawn(representation, team, toPosition);
    }

    @Override
    protected List<Direction> getDirections() {
        return DIRECTIONS;
    }
}
