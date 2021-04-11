package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public final class Team {
    private final PiecePosition piecePosition;
    private final PieceCaptured pieceCaptured;
    private final Score score;

    public Team(final PiecePosition piecePosition, final PieceCaptured pieceCaptured, final Score score) {
        this.piecePosition = piecePosition;
        this.pieceCaptured = pieceCaptured;
        this.score = score;
    }

    public static Team blackTeam() {
        return new Team(PiecePosition.initBlackPosition(), new PieceCaptured(), new Score());
    }

    public static Team whiteTeam() {
        return new Team(PiecePosition.initWhitePosition(), new PieceCaptured(), new Score());
    }

    public Piece choosePiece(final Position position) {
        return piecePosition.choosePiece(position);
    }

    public void movePiece(final Position current, final Position destination) {
        piecePosition.movePiece(current, destination);
    }

    public void moveCastlingRook(final Position destination) {
        piecePosition.moveCastlingRook(destination);
    }

    public boolean havePiece(final Position position) {
        return piecePosition.havePiece(position);
    }

    public Piece deletePiece(final Position destination) {
        return piecePosition.deletePiece(destination);
    }

    public void promotePiece(final Position position) {
        piecePosition.promote(position);
    }

    public void catchPiece(final Piece piece) {
        pieceCaptured.add(piece);
    }

    public double calculateScore() {
        return score.calculateScore(currentPiecePosition());
    }

    public Map<Position, Piece> currentPiecePosition() {
        return piecePosition.getPiecePosition();
    }
}
