package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;

public interface Piece {

    String display();

    boolean isSameColor(Color color);

    boolean isEmpty();

    boolean isKing();

    boolean isPawn();

    double score();

    List<List<Position>> movablePositions(Position position);

    List<List<Position>> killablePositions(Position position);
}
