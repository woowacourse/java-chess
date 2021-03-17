package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public interface MoveCondition {
    boolean isSatisfyBy(Board board, Piece source, Position target);
}
