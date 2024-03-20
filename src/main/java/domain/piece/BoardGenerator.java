package domain.piece;

import domain.position.Position;

import java.util.Map;

public interface BoardGenerator {

    Map<Position, Piece> generate();
}
