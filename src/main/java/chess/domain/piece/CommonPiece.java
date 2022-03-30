package chess.domain.piece;

import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonPiece extends Piece {

    protected CommonPiece(PieceType pieceType, Color color) {
        super(pieceType, color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public List<Position> calculatePathToValidate(final Position current, final Position target,
                                                  final Piece targetPiece) {
        Direction direction = findValidDirection(current, target);
        List<Position> path = new ArrayList<>();
        Position moved = current.move(direction);
        while (!moved.equals(target)) {
            path.add(moved);
            moved = moved.move(direction);
        }
        if (hasSameColor(targetPiece)) {
            path.add(target);
        }
        return path;
    }
}
