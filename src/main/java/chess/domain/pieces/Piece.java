package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.Point;

import java.util.List;

public interface Piece {
    List<Direction> range(Point p1, Point p2, Piece piece);
}
