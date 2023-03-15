package chess.domain;

import chess.domain.piece.NoPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Team;
import chess.domain.position.Position;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public void changePiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    boolean isSamePiece(final Class<Piece> pieceType) {
        return piece.getClass() == pieceType;
    }

    boolean isEmpty() {
        return piece.getClass() == NoPiece.class;
    }

    public boolean isSameTeam(final Team team) {
        return piece.isSameTeam(team);
    }

    boolean canAttack(final Position endPosition) {
        return piece.canAttack(position, endPosition);
    }

    boolean canMove(final Position startPosition, final Position endPosition) {
        return piece.canMove(startPosition, endPosition);
    }

    public void moveTo(Turn turn, final Square destination) {
        piece.addTrace(turn, position);
        destination.changePiece(piece);
        changePiece(NoPiece.getInstance());
    }
}
