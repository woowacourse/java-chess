package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Position;

public abstract class Piece {
    private final Details details;
    private final Directions directions;
    private List<Position> movablePositions;
    private boolean moved;

    public Piece(Details details, Directions directions) {
        this(details, directions, new ArrayList<>(), false);
    }

    public Piece(Details details, Directions directions,
        List<Position> movablePositions, boolean moved) {
        this.details = details;
        this.directions = directions;
        this.movablePositions = movablePositions;
        this.moved = moved;
    }
}
