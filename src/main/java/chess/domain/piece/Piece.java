package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private final Details details;
    private final Directions directions;
    private Position currentPosition;
    private List<Position> movablePositions;
    private boolean moved;

    public Piece(Details details, Directions directions, Position position) {
        this(details, directions, new ArrayList<>(), position, false);
    }

    public Piece(Details details, Directions directions,
        List<Position> movablePositions, Position position, boolean moved) {
        this.details = details;
        this.directions = directions;
        this.movablePositions = movablePositions;
        this.currentPosition = position;
        this.moved = moved;
    }

    public boolean isSameColor(TeamColor teamColor) {
        return details.isSameColor(teamColor);
    }

    public Position currentPosition() {
        return currentPosition;
    }
}
