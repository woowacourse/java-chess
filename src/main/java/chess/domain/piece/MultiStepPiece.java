package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiStepPiece extends Piece {
    protected MultiStepPiece(Color color, PieceType pieceType, Set<Direction> directions) {
        super(color, pieceType, directions);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions.forEach(direction -> addMoves(board, direction, currentPosition, movablePositions));

        return movablePositions;
    }

    private void addMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (!position.canMoveNext(direction)) {
            return;
        }

        Position nextPosition = position.next(direction);
        Piece piece = board.findPieceByPosition(nextPosition);

        if (!isSameColor(piece)) {
            movablePositions.add(nextPosition);
        }

        if (piece.isEmpty()) {
            addMoves(board, direction, nextPosition, movablePositions);
        }
    }
}
