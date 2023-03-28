package dao;

import chess.ChessGame;
import java.util.List;

public interface ChessGameDao {

    List<String> gameIds();

    void save(ChessGame chessGame, final String gameId);

    String createChessStatus(final ChessGame chessGame);

    ChessGame select(final String gameId);

    void update(ChessGame chessGame, final String gameId);

    void reset(final String gameId);
}
