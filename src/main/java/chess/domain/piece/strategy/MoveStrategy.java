package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;

public interface MoveStrategy {

    boolean isMovable(Piece piece, Board board);
}
