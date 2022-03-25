package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.piece.Color;

public interface MoveStrategy {

    boolean isValidateCanMove(Color color, Position from, Position to);

//    List<Position> getRoute(Position from, Position to);
}
