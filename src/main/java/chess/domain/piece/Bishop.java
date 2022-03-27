package chess.domain.piece;

import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team, new BishopMoveStrategy());
    }
}
