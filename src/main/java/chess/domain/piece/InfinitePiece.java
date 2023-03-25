package chess.domain.piece;

import java.util.Set;

import chess.domain.game.Team;
import chess.domain.move.Move;

public abstract class InfinitePiece extends Piece {

    public InfinitePiece(Team team, Set<Move> moves) {
        super(team, moves);
    }

    @Override
    protected boolean compareMove(Move pieceMove, Move move) {
        return pieceMove.equals(move.getUnitMove());
    }
}
