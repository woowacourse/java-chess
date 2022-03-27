package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {

    public King(final Team team) {
        super(team, new KingMoveStrategy());
    }
}
