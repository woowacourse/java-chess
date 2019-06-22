package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    private Map<Point, Optional<Piece>> points;
    private boolean isKingDead;

    public Board(Map<Point, Optional<Piece>> points) {
        this.points = new HashMap<>(points);
        this.isKingDead = false;
    }

    public Optional<Piece> get(Point point) {
        return points.get(point);
    }

    // TODO: 2019-06-22 private!!
    public boolean canMove(Point prev, Point next) {
        Optional<Piece> prevPiece = points.get(prev);
        if (!prevPiece.isPresent()) {
            return false;
        }

        DirectionType direction = DirectionType.valueOf(prev, next);

        if (!DirectionType.getKnightDirection().contains(direction)) {
            int size = next.maxAbsoluteValue(prev);

            for (int i = 1; i < size - 1; i++) {
                Point moving = prev.moveOneStep(direction, i);
                if (points.get(moving).isPresent()) {
                    return false;
                }
            }
        }

        // TODO pawn
        Optional<Piece> nextPiece = points.get(next);
        if (nextPiece.isPresent() && prevPiece.get() instanceof Pawn) {
            // TODO: 2019-06-22 대각선 -> 직선으로
            if (!DirectionType.diagonalDirection().contains(direction)) {
                return false;
            }
        }

        if (nextPiece.isPresent() && points.get(prev).get().isSamePlayerType(nextPiece.get())) {
            return false;
        }
        return true;
    }

    public void move(Point prev, Point next) {
        if (canMove(prev, next) && points.get(prev).get().isMovable(prev, next)) {
            Optional<Piece> nextPiece = points.get(next);
            if (nextPiece.isPresent() && nextPiece.get() instanceof King) {
                isKingDead = true;
            }
            points.put(next, points.get(prev));
            points.put(prev, Optional.empty());
        }
    }


    public boolean isOwnPiece(Point prev, PlayerType playerType) {
        return points.get(prev).orElseThrow(IllegalArgumentException::new).isSamePlayerType(playerType);
    }

    public boolean isKingDead() {
        return isKingDead;
    }


}
