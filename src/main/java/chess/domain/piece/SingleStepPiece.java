package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class SingleStepPiece extends Piece {

    protected SingleStepPiece(Color color, PieceType pieceType, Set<Direction> directions) {
        super(color, pieceType, directions);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();

        directions.forEach(direction -> addMoves(currentPosition, board, direction, movablePositions));

        return movablePositions;
    }

    private void addMoves(Position position, Board board, Direction direction, Set<Position> movablePositions) {
        if (!position.canMoveNext(direction)) {
            return;
        }

        position = position.next(direction);
        Piece findPiece = board.findPieceByPosition(position);

        if (isNotSameColor(findPiece)) {
            movablePositions.add(position);
        }
    }
}
