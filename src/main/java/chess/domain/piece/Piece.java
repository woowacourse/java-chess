package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

public interface Piece {

    void move(Position from, Position to, Piece target);

    Position getPosition();

    Color getColor();

    PieceType getPieceType();

    double getScore();
}
