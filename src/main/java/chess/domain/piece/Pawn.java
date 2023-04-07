package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PawnMoveStrategy;
import chess.domain.strategy.piecemovestrategy.PieceType;

public final class Pawn implements Piece {

    private final PawnMoveStrategy pawnMoveStrategy;
    private Position position;

    public Pawn(final Position position, final PawnMoveStrategy pawnMoveStrategy) {
        this.position = position;
        this.pawnMoveStrategy = pawnMoveStrategy;
    }

    @Override
    public void move(final Position from, final Position to, final Piece target) {

    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return pawnMoveStrategy.getColor();
    }

    @Override
    public PieceType getPieceType() {
        return pawnMoveStrategy.getPieceType();
    }

    @Override
    public double getScore() {
        return getPieceType().getScore();
    }
}