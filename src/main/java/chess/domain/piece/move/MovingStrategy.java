package chess.domain.piece.move;

import chess.domain.board.Board;
import chess.domain.board.Point;

public interface MovingStrategy {
    boolean move(Board board, Point from, Point to);
}
