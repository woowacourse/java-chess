package chess.domain.piece;

import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends Piece{

    public Pawn(final Team team) {
        super(team, new PawnMoveStrategy());
    }
}
