package chess.dao;

import chess.domain.game.ChessGame;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ChessDAO {
    long createChessGame(ChessGame chessGame) throws SQLException;

    void addBoard(long chessGameId, ChessGame chessGame) throws SQLException;

    void deleteGame(long chessGameId) throws SQLException;

    ChessGame findGameById(long id) throws SQLException;

    List<Long> getRoomId() throws SQLException;

    Map<Long, ChessGame> getDatabase() throws SQLException;
}
