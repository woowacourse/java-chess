package domain;

import domain.piece.CommonMovablePiece;
import domain.position.Position;
import java.util.Map;

@FunctionalInterface
public interface BoardGenerator {

    Map<Position, CommonMovablePiece> generate();
}
