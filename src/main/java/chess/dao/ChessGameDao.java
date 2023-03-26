package chess.dao;

import chess.chessgame.ChessGame;

import java.util.Optional;

public interface ChessGameDao {
    void save(ChessGame chessGame);

    void delete();

    void update(ChessGame chessGame);

    Optional<ChessGameDto> find();
}
