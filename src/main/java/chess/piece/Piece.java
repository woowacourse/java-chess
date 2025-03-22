package chess.piece;

import chess.board.Position;

public interface Piece {

    void moveToDestination(Position start, Position end);
}
