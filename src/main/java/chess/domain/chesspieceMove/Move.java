package chess.domain.chesspieceMove;

import chess.domain.Position;

import java.util.List;

public interface Move {
    List<Position> move(Position source, Position target);

    boolean isInRoute(Position source, Position target);
}
