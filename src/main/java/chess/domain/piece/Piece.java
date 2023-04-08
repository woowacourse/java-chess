package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.piecemovestrategy.PieceType;

public interface Piece {

    void move(Position from, Position to, Piece target);

    boolean isEmpty();

    boolean isFriend(Piece target);

    boolean isEnemy(Piece target);

    boolean isColorMatch(Color color);

    Position getPosition();

    Color getColor();

    PieceType getPieceType();

    double getScore();
}
