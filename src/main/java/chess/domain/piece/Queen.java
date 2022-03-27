package chess.domain.piece;

import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends Piece{

    public Queen(final Team team) {
        super(team, new QueenMoveStrategy());
    }
}
