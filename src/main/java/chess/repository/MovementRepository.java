package chess.repository;

import chess.entity.Movement;

import java.sql.SQLException;
import java.util.List;

public interface MovementRepository {

    Movement save(Movement entity) throws SQLException;

    List<Movement> findAllByChessId(Long chessId) throws SQLException;

    void deleteAll() throws SQLException;
}
