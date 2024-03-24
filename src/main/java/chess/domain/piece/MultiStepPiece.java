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
        if (position.canMoveNext(direction)) {
            Position nextPosition = position.next(direction);
            Piece findPiece = board.findPieceByPosition(nextPosition);

            addPositionIfValid(board, direction, movablePositions, findPiece, nextPosition);
        }
    }

    private void addPositionIfValid(Board board, Direction direction, Set<Position> movablePositions, Piece findPiece,
                                    Position nextPosition) {
        if (isNotSameColor(findPiece)) {
            movablePositions.add(nextPosition);

            addMovesIfPieceEmpty(board, direction, movablePositions, findPiece, nextPosition);
        }
    }

    private void addMovesIfPieceEmpty(Board board, Direction direction, Set<Position> movablePositions,
                                      Piece findPiece,
                                      Position nextPosition) {
        if (findPiece.isEmpty()) {
            addMoves(board, direction, nextPosition, movablePositions);
        }
    }
}
