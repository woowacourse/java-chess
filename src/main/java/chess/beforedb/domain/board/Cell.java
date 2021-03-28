package chess.beforedb.domain.board;

import chess.beforedb.domain.piece.Piece;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.MoveRoute;

public class Cell {
    private static final String EMPTY_STATUS = ".";

    private Piece piece;

    public Cell(Piece piece) {
        this.piece = piece;
    }

    public void put(Piece piece) {
        this.piece = piece;
    }

    public boolean canMoveTo(MoveRoute moveRoute, Board board) {
        return piece.canMoveTo(moveRoute, board);
    }

    public void movePieceTo(Cell targetCell) {
        targetCell.put(piece);
        piece = null;
    }

    public TeamColor teamColor() {
        return piece.getTeamColor();
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public PieceType pieceType() {
        return piece.getPieceType();
    }

    public Piece getPiece() {
        return piece;
    }

    public String status() {
        if (piece != null) {
            return piece.getName();
        }
        return EMPTY_STATUS;
    }
}
