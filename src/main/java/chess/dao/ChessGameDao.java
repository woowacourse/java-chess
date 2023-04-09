package chess.dao;

import chess.domain.chessgame.ChessGame;

import java.util.Optional;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    void deleteGameById(int id);

    void update(ChessGame chessGame);

    Optional<ChessGame> findById(int id);
}
