package chess.piece;

import chess.Position;
import chess.board.Board;

public abstract class Piece {
    public abstract void validateMovable(Board board, Position start, Position goal);
}
