package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class MoveStrategy {

    public abstract boolean isValidateCanMove(Color color, Position from, Position to);

    public boolean isValidateCanMove(Color color, Piece targetPiece, Position from, Position to) {
        return true;
    }

    public List<Position> getRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> positions = new ArrayList<>();
        Position movedPosition = from.advancePosition(direction);

        while (!movedPosition.equals(to)) {
            positions.add(movedPosition);
            movedPosition = movedPosition.advancePosition(direction);
        }
        return positions;
    }
}
