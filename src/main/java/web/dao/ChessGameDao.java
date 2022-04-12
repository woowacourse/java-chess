package web.dao;

import chess.domain.game.ChessGame;
import java.sql.SQLException;
import java.util.List;
import web.dto.ChessGameResponse;

public interface ChessGameDao {

    Long createGame(ChessGame chessGame) throws SQLException;

    List<ChessGameResponse> findRunningGames() throws SQLException;

    ChessGameResponse findByGameId(String gameId) throws SQLException;

    void updateTurn(Long gameId, String turn) throws SQLException;

    String findTurn(String gameId) throws SQLException;

    void updateGameEnd(Long gameId) throws SQLException;
}
