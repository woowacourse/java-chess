package chess.piece;

import chess.Board;
import chess.Position;

public interface Piece {
    Position getPosition();

    String getName();

    boolean isBlack();

    void moveTo(Position targetPosition, Board board);

    boolean isEnemyWith(Piece piece);
}
