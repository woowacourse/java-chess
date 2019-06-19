package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

public interface Piece {
    Direction move(Point p1, Point p2);

    Direction attack(Point p1, Point p2);

    boolean isAlly(Piece piece);

    boolean isTurn(ChessTeam team);
}
