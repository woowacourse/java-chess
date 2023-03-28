package dao;

import chess.ChessGame;
import java.util.List;

public interface ChessGameDao {

    List<String> gameIds();

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);

    void reset();
}
