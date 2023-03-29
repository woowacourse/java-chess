package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.Running;
import chess.domain.position.Position;

import java.sql.Connection;

public interface ChessGameDao {

    Connection getConnection();

    Long createGame();

    void saveGame(ChessGame chessGame);

    void updateGame(ChessGame chessGame, Position source, Position target);

    Running findGameById(Long gameId);

    void deleteGameById(Long gameId);
}
