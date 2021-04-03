package chess.dao.game;

import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.GameStatusEntity;
import java.sql.SQLException;
import java.util.List;

public interface ChessGameRepository {
    ChessGameEntity save(ChessGameEntity chessRoomEntity) throws SQLException;

    ChessGameEntity findById(Long id) throws SQLException;

    List<ChessGameEntity> findAll() throws SQLException;

    GameStatusEntity findStatusByGameId(Long gameId) throws SQLException;

    ChessGameEntity updateCurrentTurnTeamColor(ChessGameEntity chessGameEntity) throws SQLException;

    void remove(Long gameId) throws SQLException;

    void removeAll() throws SQLException;
}
