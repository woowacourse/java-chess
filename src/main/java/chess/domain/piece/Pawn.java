package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private static final int DEFAULT_WHITE_RANK = 2;
    private static final int DEFAULT_BLACK_RANK = 7;

    private Pawn(Color color, Set<Direction> directions) {
        super(color, PieceType.PAWN, directions);
    }

    public static Pawn ofBlack() {
        Set<Direction> directions = Direction.ofBlackPawn();
        return new Pawn(Color.BLACK, directions);
    }

    public static Pawn ofWhite() {
        Set<Direction> directions = Direction.ofWhitePawn();
        return new Pawn(Color.WHITE, directions);
    }

    @Override
    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions.forEach(direction -> {
            if (!currentPosition.canMoveNext(direction)) {
                return;
            }
            addForwardMoves(board, direction, currentPosition, movablePositions);
            addDiagonalMoves(board, direction, currentPosition, movablePositions);
        });

        return movablePositions;
    }

    private void addForwardMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (direction.isDiagonal()) {
            return;
        }
        int currentRank = position.getRank();
        Position nextPosition = position.next(direction);
        Piece firstPiece = board.findPieceByPosition(nextPosition);
        if (firstPiece.isEmpty()) {
            movablePositions.add(nextPosition);
            addMultipleForwardMoves(board, direction, movablePositions, currentRank, nextPosition);
        }
    }

    private void addMultipleForwardMoves(Board board, Direction direction, Set<Position> movablePositions,
                                         int currentRank,
                                         Position nextPosition) {
        if (isStartingPosition(direction, currentRank)) {
            Position nextNextPosition = nextPosition.next(direction);
            Piece secondPiece = board.findPieceByPosition(nextNextPosition);
            if (secondPiece.isEmpty()) {
                movablePositions.add(nextNextPosition);
            }
        }
    }

    private void addDiagonalMoves(Board board, Direction direction, Position position, Set<Position> movablePositions) {
        if (direction.isDiagonal()) {
            position = position.next(direction);
            Piece piece = board.findPieceByPosition(position);

            if (!isSameColor(piece) && !piece.isEmpty()) {
                movablePositions.add(position);
            }
        }
    }

    private boolean isStartingPosition(Direction direction, int currentRank) {
        return (DEFAULT_WHITE_RANK == currentRank && direction == Direction.NORTH) ||
                (DEFAULT_BLACK_RANK == currentRank && direction == Direction.SOUTH);
    }
}
