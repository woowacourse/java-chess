package chess.dao;

import chess.dto.RoundDto;

import java.sql.SQLException;
import java.util.List;

public interface RoundDao {
    void addRound(RoundDto roundDto) throws SQLException;

    List<RoundDto> selectRound() throws SQLException;
}
