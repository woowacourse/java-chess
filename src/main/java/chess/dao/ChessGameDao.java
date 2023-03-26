package chess.dao;

import chess.domain.ChessGame;

import java.util.List;

public interface ChessGameDao {
    int save(final ChessGame chessGame);

    ChessGame select(final int id);

    List<Integer> selectAllId();

    void update(final ChessGame chessGame);

    void delete(final ChessGame chessGame);
}
