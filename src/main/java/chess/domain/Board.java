package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Spot, Piece> pieces;

    public Board(Map<Spot, Piece> pieces) {
        this.pieces = pieces;
    }

    public Board move(Spot startSpot, Spot endSpot) {
        validate(startSpot, endSpot);
        Piece piece = pieces.get(startSpot);
        pieces.replace(endSpot, piece);
        pieces.replace(startSpot, Empty.getInstance());
        return new Board(pieces);
    }

    private void validate(Spot startSpot, Spot endSpot) {
        moveValidate(startSpot, endSpot);
        checkPath(startSpot, endSpot);
    }

    private boolean moveValidate(Spot startSpot, Spot endSpot) {
        Piece startPointPiece = pieces.get(startSpot);
        Piece endPointPiece = pieces.get(endSpot);

        if (startPointPiece.empty()) {
            return false;
        }
        if (endPointPiece.empty()) {
            return startPointPiece.isMovable(startSpot, endSpot);
        }
        if (startPointPiece.sameTeam(endPointPiece)) {
            return false;
        }
        return startPointPiece.isAttackable(startSpot, endSpot);
    }

    private boolean checkPath(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);

        MovementUnit movementUnit = MovementUnit.direction(distanceX, distanceY);
        Spot nextSpot = startSpot;
        while (nextSpot == endSpot) {
            nextSpot = startSpot.nextSpot(movementUnit, distanceX, distanceY);
            if (!pieces.get(nextSpot).empty()) {
                return false;
            }
        }
        return true;
    }

    public Map<Spot, Piece> getPieces(Team team) {
        Map<Spot, Piece> selectedPieces = new HashMap<>();
        pieces.forEach((spot, piece) -> {
            if (piece.checkTeam(team)) {
                selectedPieces.put(spot, piece);
            }
        });
        return selectedPieces;
    }

    //TODO 파라미터의 갯수를 늘리지 않고 재귀로 할 수 있는 방법이 있을까?
//    public boolean checkPath(Spot startSpot, Spot endSpot, MovementUnit movementUnit) {
//        if (startSpot == endSpot) {
//            return true;
//        }
//        if (pieces.get(startSpot).empty()) {
//            return checkPath(startSpot.nextSpot(movementUnit), endSpot, movementUnit);
//        }
//        return false;
//    }
}
