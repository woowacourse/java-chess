package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;

public class Knight extends Piece {

    public Knight(final Team team) {
        super(team, new KingMoveStrategy());
    }
}
