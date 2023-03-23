package chess.domain;

import chess.domain.piece.NoPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Map;

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
        return piece.findType() == PieceType.NOPIECE;
    }

    public boolean isSameTeam(final Team team) {
        return piece.isSameTeam(team);
    }

    public boolean isKing() {
        return piece.findType() == PieceType.KING;
    }

    public boolean canAttack(final Position endPosition) {
        return piece.canAttack(position, endPosition);
    }

    public boolean canMove(final Position startPosition, final Position endPosition) {
        return piece.canMove(startPosition, endPosition);
    }

    public void moveTo(Turn turn, final Square destination) {
        piece.addTrace(turn, position);
        destination.changePiece(piece);
        changePiece(NoPiece.getInstance());
    }

    private void changePiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isSameFileAndTeam(File file, Team team) {
        return position.isSameFile(file) && piece.isSameTeam(team);
    }

    public PieceType findPieceType() {
        return piece.findType();
    }

    public Score findPieceScore(Map<PieceType, Long> pieceCountBoard) {
        return piece.calculateScore(pieceCountBoard);
    }

    public Piece getPiece() {
        return piece;
    }
}
