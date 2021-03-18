package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import java.util.List;

public interface MoveStrategy {

    boolean isMovable(Piece piece, Board board);

    List<Direction> getDirections();
}
