package domain.chessboard;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public interface BoardGenerator {

    Map<Position, Piece> generate();
}
