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

    Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    boolean isEmpty() {
        return piece.findType() == PieceType.NOPIECE;
    }

    boolean isSameTeam(final Team team) {
        return piece.isSameTeam(team);
    }

    boolean isKing() {
        return piece.findType() == PieceType.KING;
    }

    boolean canAttack(final Position destination) {
        return piece.canAttack(position, destination);
    }

    boolean canMove(final Position source, final Position destination) {
        return piece.canMove(source, destination);
    }

    void moveTo(Turn turn, final Square destination) {
        piece.addTrace(turn, position);
        destination.changePiece(piece);
        changePiece(NoPiece.getInstance());
    }

    private void changePiece(Piece piece) {
        this.piece = piece;
    }

    void removePiece() {
        this.piece = NoPiece.getInstance();
    }

    boolean isSameFileAndTeam(File file, Team team) {
        return position.isSameFile(file) && piece.isSameTeam(team);
    }

    PieceType findPieceType() {
        return piece.findType();
    }

    Score findPieceScore(Map<PieceType, Long> pieceCountBoard) {
        return piece.calculateScore(pieceCountBoard);
    }

    boolean isSoonMovedTwo(final Turn turn) {
        return piece.isSoonMovedTwo(turn, position);
    }

    public Piece getPiece() {
        return piece;
    }
}
