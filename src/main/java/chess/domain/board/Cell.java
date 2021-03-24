package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;

import java.util.Objects;

public class Cell {

    private Piece piece;

    public void put(Piece piece) {
        this.piece = piece;
    }

    public void movePieceTo(Cell targetCell) {
        Piece pieceOnCurrentCell = getPiece();
        targetCell.put(pieceOnCurrentCell);
        this.piece = null;
    }

    public Piece getPiece() {
        if (isEmpty()) {
            throw new IllegalStateException("현재 위치에 기물이 없습니다.");
        }
        return piece;
    }

    public boolean isEmpty() {
        return Objects.isNull(piece);
    }

    public boolean hasMovablePiece(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        return getPiece().isMovable(chessBoard, current, destination);
    }

    public boolean isEmptyOrHasEnemy(TeamType teamType) {
        return isEmpty() || !isTeamOf(teamType);
    }

    public boolean hasEnemy(TeamType teamType) {
        return !isEmpty() && !isTeamOf(teamType);
    }

    public boolean isTeamOf(TeamType teamType) {
        return !isEmpty() && getPiece().isTeamOf(teamType);
    }

    public boolean hasPieceOf(Class<? extends Piece> pieceType) {
        return !isEmpty() && getPiece().isPieceOf(pieceType);
    }

    public TeamType getTeamType() {
        return getPiece().getTeamType();
    }
}
