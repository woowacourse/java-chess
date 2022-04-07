package chess.domain.piece.common;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
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

    public List<Position> calculatePathToValidate(Position current, Position target, Piece targetPiece) {
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

    @Override
    protected Direction findValidDirection(Position current, Position target) {
        int columnDifference = target.calculateColumnDifference(current);
        int rowDifference = target.calculateRowDifference(current);
        Direction direction = Direction.calculate(columnDifference, rowDifference);
        validateDirection(direction);
        return direction;
    }
}
