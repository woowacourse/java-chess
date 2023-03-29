package chess.database;

import chess.dto.MoveDto;

import java.util.List;

public interface ChessGameDao {

    void saveNotation(final String fromPosition, final String toPosition);

    List<MoveDto> selectNotation();

    void delete();
}
