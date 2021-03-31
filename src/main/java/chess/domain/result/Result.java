package chess.domain.result;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public interface Result {

    String infoAsString();

    Map<Position, Piece> infoAsMap();

    List<Position> infoAsList();
}
