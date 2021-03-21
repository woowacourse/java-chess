package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private static final int CURRENT_PIECE_INDEX = 0;

    private final List<Piece> pieceOnCell = new ArrayList<>();

    public void put(Piece piece) {
        if (!pieceOnCell.isEmpty()) {
            pieceOnCell.clear();
        }
        pieceOnCell.add(piece);
    }

    public void movePieceTo(Cell targetCell) {
        Piece pieceOnCurrentCell = getPiece();
        targetCell.put(pieceOnCurrentCell);
        pieceOnCell.clear();
    }

    public Piece getPiece() {
        if (pieceOnCell.isEmpty()) {
            throw new IllegalStateException("현재 위치에 기물이 없습니다.");
        }
        return pieceOnCell.get(CURRENT_PIECE_INDEX);
    }

    public boolean hasMovablePiece(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        return getPiece().isMovable(chessBoard, current, destination);
    }

    public boolean isEmptyOrHasEnemy(TeamType teamType) {
        return pieceOnCell.isEmpty() || !isTeamOf(teamType);
    }

    public boolean isEmpty() {
        return pieceOnCell.isEmpty();
    }

    public boolean hasEnemy(TeamType teamType) {
        return !pieceOnCell.isEmpty() && !isTeamOf(teamType);
    }

    public boolean isTeamOf(TeamType teamType) {
        return getPiece().isTeamOf(teamType);
    }

    public boolean hasKing() {
        return getPiece().isKing();
    }

    public boolean hasPawn() {
        return getPiece().isPawn();
    }

    public double getPieceScore() {
        return getPiece().getScore();
    }

    public TeamType getTeamType() {
        return getPiece().getTeamType();
    }
}
