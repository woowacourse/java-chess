package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;

public class Cell {
    private Piece piece;

    public Cell(Piece piece) {
        this.piece = piece;
    }

    public void put(Piece piece) {
        this.piece = piece;
    }

    public boolean isMovableTo(MoveRoute moveRoute, Board board) {
        return piece.isMovableTo(moveRoute, board);
    }

    public void movePieceTo(Cell targetCell) {
        targetCell.put(piece);
        piece = null;
    }

    public TeamColor teamColor() {
        return piece.color();
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public PieceType pieceType() {
        return piece.type();
    }

    public Piece piece() {
        return piece;
    }
}
