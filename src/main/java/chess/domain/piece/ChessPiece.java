package chess.domain.piece;

import chess.domain.ChessBoardPosition;

public interface ChessPiece {

    void move(ChessBoardPosition targetPosition);
}
