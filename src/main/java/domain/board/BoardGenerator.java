package domain.board;

import domain.piece.Piece;
import java.util.Map;

public interface BoardGenerator {

    Map<Position, Piece> generate();
}
