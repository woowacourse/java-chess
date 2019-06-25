package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Spot, Piece> pieces;

    public Board(Map<Spot, Piece> pieces) {
        this.pieces = pieces;
    }

    public Board move(Spot startSpot, Spot endSpot) {
        if (checkMovable(startSpot, endSpot)) {
            pieces.replace(endSpot, pieces.get(startSpot));
            pieces.replace(startSpot, Empty.getInstance());
            return new Board(pieces);
        }
        return this;
    }

    private boolean checkMovable(Spot startSpot, Spot endSpot) {
        Piece selectedPiece = pieces.get(startSpot);
        Piece targetPiece = pieces.get(endSpot);

        if (selectedPiece.empty() || selectedPiece.sameTeam(targetPiece)) {
            return false;
        }

        Spot nextSpot = startSpot.nextSpot(startSpot.calculateMovement(endSpot));

        return checkPath(nextSpot, endSpot, selectedPiece);
    }

    private boolean checkPath(Spot startSpot, Spot endSpot, Piece selectedPiece) {
        MovementUnit movementUnit = startSpot.calculateMovement(endSpot);

        if (startSpot == endSpot) {
            Piece targetSpot = pieces.get(endSpot);
            return targetSpot.empty() ?
                    selectedPiece.isMovable(startSpot, endSpot)
                    : selectedPiece.isAttackable(startSpot,endSpot);
        }

        if (pieces.get(startSpot) != Empty.getInstance()) {
            return false;
        }

        return checkPath(startSpot.nextSpot(movementUnit), endSpot, selectedPiece);
    }

    public Map<Spot, Piece> getTeamPieces(Team team) {
        return pieces.entrySet()
                .stream()
                .filter(spotPieceEntry -> spotPieceEntry.getValue().checkTeam(team))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Spot, Piece> getPieces() {
        return pieces;
    }
}
