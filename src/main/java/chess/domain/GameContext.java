package chess.domain;

import java.sql.SQLException;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public interface GameContext {
    int addGame(Player white, Player black) throws SQLException;

    boolean isEmpty() throws SQLException;

    Game findGameById(int id) throws SQLException;

    Map<Position, Piece> findBoardById(int id) throws SQLException;

    void resetGameById(int id) throws SQLException;

    void finishGameById(int id) throws SQLException;

    double getScoreById(int id, Side side) throws SQLException;

    Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException;

    Map<Integer, Map<Side, Double>> getScoreContexts() throws SQLException;

    Map<Side, Double> getScoresById(int id) throws SQLException;

    void addMoveByGameId(int id, MoveRequestDto dto) throws SQLException;
}
