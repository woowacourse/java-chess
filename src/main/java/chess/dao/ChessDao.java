package chess.dao;

import chess.entity.ChessGame;

import java.util.List;
import java.util.Optional;

public interface ChessDao {
    ChessGame save(ChessGame entity);

    Optional<ChessGame> findById(Long id);

    void update(ChessGame entity);

    List<ChessGame> findAll();

    List<ChessGame> findAllByActive();

    void deleteAll();
}
