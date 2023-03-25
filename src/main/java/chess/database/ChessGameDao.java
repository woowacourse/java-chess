package chess.database;

import chess.domain.position.Position;

import java.util.List;

public interface ChessGameDao {

    void save(final Position fromPosition, final Position toPosition);

    List<Position> select();
}
