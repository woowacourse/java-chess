package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;

public class Cell {
    private Piece piece;

    public void put(Piece piece) {
        this.piece = piece;
    }

    public boolean isMovable(TeamType teamType) {
        return piece == null || !piece.isTeamOf(teamType);
    }

    public boolean hasEnemy(TeamType teamType) {
        return piece != null && !piece.isTeamOf(teamType);
    }

    public boolean isMovableTo(Board board, Coordinate currentCoordinate,
        Coordinate targetCoordinate) {
        return piece.isMovableTo(board, currentCoordinate, targetCoordinate);
    }

    public void movePieceTo(Cell targetCell) {
        targetCell.put(piece);
        piece = null;
    }

    public boolean isTeamOf(TeamType teamType) {
        if (piece == null) {
            return false;
        }
        return piece.isTeamOf(teamType);
    }

    public boolean hasPawn() {
        if (piece == null) {
            return false;
        }
        return piece.isPawn();
    }

    public double getPieceScore() {
        return piece.getScore();
    }

    public boolean hasKing() {
        if (piece == null) {
            return false;
        }
        return piece.isKing();
    }

    public TeamType getTeamType() {
        return piece.getTeamType();
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }
}
