package chess.dao;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public interface SquareDao {

    void save(Position position, Piece piece);

    Map<String, String> find();

    void delete();
}
