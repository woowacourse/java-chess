package chess.domain.piece;

import chess.domain.position.Position;

public interface PieceState {
    PieceState checkIfMovable(Position toPosition, Position fromPosition);
}
