package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    private final Set<Direction> directions;

    public Rook(Color color) {
        super(color, PieceType.ROOK);
        this.directions = Direction.ofStraight();
    }

    public Set<Position> calculateMovablePositions(Position currentRookPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        return movablePositions;
    }
}
