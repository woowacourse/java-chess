package chess.dao;

import chess.domain.chessgame.ChessGame;

import java.util.Optional;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    void delete();

    void update(ChessGame chessGame);

    Optional<ChessGame> findById(long id);
}
