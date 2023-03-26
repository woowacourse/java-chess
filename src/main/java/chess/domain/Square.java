package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.position.File;
import chess.domain.position.Position;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    public boolean isEmpty() {
        return piece.getClass() == Blank.class;
    }

    public boolean isSameTeam(final Team team) {
        return piece.isSameTeam(team);
    }

    public boolean isKing() {
        return piece.getClass() == King.class;
    }

    public boolean canAttack(final Position destination) {
        return piece.canAttack(position, destination);
    }

    public boolean canMove(final Position source, final Position destination) {
        return piece.canMove(source, destination);
    }

    public void moveTo(final Square destination) {
        destination.changePiece(piece);
        changePiece(Blank.getInstance());
    }

    private void changePiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasSameRole(Role role) {
        return piece.isSameRole(role);
    }

    public double getScore() {
        return piece.getScore();
    }

    public boolean isSameFile(final File file) {
        return position.isSameFile(file);
    }

    public Position getPosition() {
        return position;
    }
}
