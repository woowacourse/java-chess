package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.Map;

public interface Board {
    Square findByPosition(Position position);

    Map<Position, Square> board();

    MoveResult move(Position from, Position to);

    MoveOrder createMoveOrder(Position from, Position to);
    
    Map<Color, Double> getScoreMap();
}
