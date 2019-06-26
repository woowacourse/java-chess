package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    private final Map<Spot, Piece> pieces;

    public Board(Map<Spot, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public Board move(Spot startSpot, Spot endSpot) {
        Piece selectedPiece = pieces.get(startSpot);
        Piece targetPiece = pieces.get(endSpot);
        if (moveValidation(startSpot, endSpot)) {
            pieces.replace(endSpot, selectedPiece);
            pieces.replace(startSpot, Empty.getInstance());
        }
        if (isKingDie(targetPiece)) {
            Team winnerTeam = selectedPiece.getTeam();
            pieces.replaceAll(((spot, piece) -> new King(winnerTeam)));
        }
        return new Board(pieces);
    }

    private boolean isKingDie(Piece targetPiece) {
        return targetPiece.getPieceType() == PieceType.KING;
    }

    private boolean moveValidation(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot) && isPieceMovement(startSpot, endSpot);
    }

    private boolean isMovable(Spot startSpot, Spot endSpot) {
        Piece selectedPiece = pieces.get(startSpot);
        Piece targetPiece = pieces.get(endSpot);

        if (selectedPiece.isEmpty() || selectedPiece.checkTeam(targetPiece)) {
            return false;
        }

        Spot nextSpot = startSpot.nextSpot(startSpot.calculateMovement(endSpot));

        return checkPath(nextSpot, endSpot);
    }

    private boolean checkPath(Spot startSpot, Spot endSpot) {
        MovementUnit movementUnit = startSpot.calculateMovement(endSpot);

        if (startSpot == endSpot) {
            return true;
        }
        Piece piece = pieces.get(startSpot);
        if (piece != Empty.getInstance()) {
            return false;
        }
        return checkPath(startSpot.nextSpot(movementUnit), endSpot);
    }

    private boolean isPieceMovement(Spot startSpot, Spot endSpot) {
        Piece selectedPiece = pieces.get(startSpot);
        Piece targetPiece = pieces.get(endSpot);

        if (targetPiece.checkTeam(Team.EMPTY)) {
            return selectedPiece.isMovable(startSpot, endSpot);
        }
        if (!selectedPiece.checkTeam(targetPiece)) {
            return selectedPiece.isAttackable(startSpot, endSpot);
        }
        return false;
    }

    public Map<Spot, Piece> getTeamPieces(Team team) {
        return pieces.entrySet()
                .stream()
                .filter(spotPieceEntry -> spotPieceEntry.getValue().checkTeam(team))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public boolean teamCheck(Spot spot, Team team) {
        return pieces.get(spot).checkTeam(team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return pieces == ((Board) o).pieces;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}

