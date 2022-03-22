package domain;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    Map<Position, Piece> generate();
}
