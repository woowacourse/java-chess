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

    boolean canAttack(final Position endPosition) {
        return piece.canAttack(position, endPosition);
    }

    boolean canMove(final Position startPosition, final Position endPosition) {
        return piece.canMove(startPosition, endPosition);
    }

    void moveTo(Turn turn, final Square destination) {
        piece.addTrace(turn, position);
        destination.changePiece(piece);
        changePiece(NoPiece.getInstance());
    }

    private void changePiece(Piece piece) {
        this.piece = piece;
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

    public Piece getPiece() {
        return piece;
    }
}
