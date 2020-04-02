package chess.domain.strategy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.List;

public interface MoveStrategy {
    List<Position> getPossiblePositions(Board board, Piece piece);

    List<Direction> getDirections();
}
