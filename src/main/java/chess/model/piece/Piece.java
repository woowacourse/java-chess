package chess.model.piece;

import chess.model.piece.movement.AttackEvaluator;
import chess.model.piece.score.PieceScore;
import chess.model.position.Distance;

public final class Piece {

    public static final Piece EMPTY = new Piece(PieceType.EMPTY, Camp.BLACK);

    private final PieceType pieceType;
    private final Camp camp;

    public Piece(final PieceType pieceType, final Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    public Piece pick() {
        if (this.pieceType.isSamePieceType(PieceType.INITIAL_PAWN)) {
            return new Piece(PieceType.PAWN, this.camp);
        }
        return this;
    }

    public boolean movable(final Distance distance, final Piece target) {
        final AttackEvaluator attackEvaluator = new AttackEvaluator(this.camp, target.camp,
                target.pieceType.isSamePieceType(PieceType.EMPTY));

        return pieceType.movable(distance, attackEvaluator);
    }

    public boolean isNotPassable() {
        return !PieceType.EMPTY.isSamePieceType(pieceType);
    }

    public boolean isSameTeam(final Camp camp) {
        return this.camp.isSameCamp(camp);
    }

    public boolean isSameType(final PieceType pieceType) {
        return this.pieceType.isSamePieceType(pieceType);
    }

    public PieceScore score() {
        return pieceType.pieceScore();
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Camp getCamp() {
        return camp;
    }
}
