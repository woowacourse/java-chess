package chess.database;

import chess.domain.position.Position;

import java.util.List;

public interface ChessGameDao {

    void save(Position fromPosition, Position toPosition);

    List<Position> select();
}
