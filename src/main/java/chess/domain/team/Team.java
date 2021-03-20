package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class Team {
    private final PiecePosition piecePosition;
    private final PieceCaptured pieceCaptured;
    private final Score score;

    public Team(final PiecePosition piecePosition) {
        this.piecePosition = piecePosition;
        this.pieceCaptured = new PieceCaptured();
        this.score = new Score();
    }

    public final Piece choosePiece(final Position position) {
        return piecePosition.choosePiece(position);
    }

    public final void movePiece(final Position current, final Position destination) {
        piecePosition.movePiece(current, destination);
    }

    public final boolean havePiece(final Position position) {
        return piecePosition.havePiece(position);
    }

    public final Piece deletePiece(final Position destination) {
        return piecePosition.deletePiece(destination);
    }

    public final void catchPiece(final Piece piece) {
        pieceCaptured.add(piece);
    }

    public final double calculateScore() {
        return score.calculateScore(currentPiecePosition());
    }

    public final Map<Position, Piece> currentPiecePosition() {
        return piecePosition.getPiecePosition();
    }
}
