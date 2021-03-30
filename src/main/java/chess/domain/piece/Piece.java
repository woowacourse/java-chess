package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.List;

public interface Piece {
    void moveToEmpty(Position to, Pieces pieces);

    void moveForKill(Position to, Pieces pieces);

    String display();

    boolean hasPosition(Position position);

    Position getPosition();

    boolean isSameColor(Color color);

    boolean isSameColor(Piece piece);

    boolean isEmpty();

    boolean isKing();

    double score();

    boolean isPawn();

    Column getColumn();

    List<List<Position>> movablePositions(Position position);

    List<List<Position>> killablePositions(Position position);
}
