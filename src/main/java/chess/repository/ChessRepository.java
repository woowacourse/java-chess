package chess.repository;

import chess.entity.ChessGame;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ChessRepository {
    ChessGame save(ChessGame entity) throws SQLException;

    Optional<ChessGame> findById(Long id) throws SQLException;

    void update(ChessGame entity) throws SQLException;

    List<ChessGame> findAll() throws SQLException;

    List<ChessGame> findAllByActive() throws SQLException;

    void deleteAll() throws SQLException;
}
