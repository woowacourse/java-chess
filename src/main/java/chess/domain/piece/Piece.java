package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {

    boolean movable(Position to, Position from);

    String getName();
}
