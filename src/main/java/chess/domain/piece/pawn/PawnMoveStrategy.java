package chess.domain.piece.pawn;

import chess.domain.board.Position;

import java.util.Set;

public interface PawnMoveStrategy {

    Set<Position> computePath(final Position source, final Position target);
}
