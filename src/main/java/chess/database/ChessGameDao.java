package chess.database;

import chess.domain.position.Position;

import java.util.List;

public interface ChessGameDao {

    void saveNotation(final Position fromPosition, final Position toPosition);

    List<Position> selectNotation();

    void delete();
}
