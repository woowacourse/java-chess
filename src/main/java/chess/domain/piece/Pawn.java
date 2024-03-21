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
            Position position = currentPosition;
            if (!position.canMoveNext(direction)) {
                return;
            }

            int currentRank = position.getRank();
            if ((DEFAULT_WHITE_RANK == currentRank && direction == Direction.NORTH) ||
                    (DEFAULT_BLACK_RANK == currentRank && direction == Direction.SOUTH)) {
                Position firstPosition = position.next(direction);
                Position secondPosition = firstPosition.next(direction);

                Piece firstPiece = board.findPieceByPosition(firstPosition);
                Piece secondPiece = board.findPieceByPosition(secondPosition);

                if (firstPiece.isEmpty() && secondPiece.isEmpty()) {
                    movablePositions.add(secondPosition);
                }
            }

            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                position = position.next(direction);
                Piece piece = board.findPieceByPosition(position);
                if (piece.isEmpty()) {
                    movablePositions.add(position);
                    return;
                }
            }

            position = position.next(direction);
            Piece piece = board.findPieceByPosition(position);

            if (!isSameColor(piece) && !piece.isEmpty()) {
                movablePositions.add(position);
            }
        });
        return movablePositions;
    }
}
