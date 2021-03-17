package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;

public interface MoveStrategy {

    void move(Position source, Position target, Board board);
}
