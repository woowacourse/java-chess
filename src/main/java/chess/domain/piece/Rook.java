package chess.domain.piece;

import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team, new RookMoveStrategy());
    }
}
