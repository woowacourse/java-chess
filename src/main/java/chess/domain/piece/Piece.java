package chess.domain.piece;

import chess.domain.location.Position;

import java.util.List;
import java.util.Map;

public interface Piece {

    String display();

    boolean isSame(Color color);

    boolean isOpposite(Color color);

    boolean isEmpty();

    boolean isKing();

    boolean isPawn();

    double score();


    List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition);

}
