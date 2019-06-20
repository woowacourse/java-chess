package chess.domain.piece;

import chess.domain.Spot;

public interface Piece {
    boolean isSameTeam(Piece piece);

    boolean isMovable(Spot spot);
}