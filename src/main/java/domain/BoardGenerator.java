package domain;

import domain.piece.unit.Piece;
import domain.position.Position;
import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    Map<Position, Piece> generate();
}
