package chess.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import chess.domain.Game;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public interface ChessService {
    Map<Integer, Map<Side, Player>> addGame(Player white, Player black) throws SQLException;

    Game findGameById(int id) throws SQLException;

    Map<Position, Piece> findBoardById(int id) throws SQLException;

    Map<Position, Piece> resetGameById(int id) throws SQLException;

    boolean finishGameById(int id) throws SQLException;

    double getScoreById(int id, Side side) throws SQLException;

    Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException;

    Map<Integer, Map<Side, Double>> getScoreContexts() throws SQLException;

    Map<Side, Double> getScoresById(int id) throws SQLException;

    boolean addMoveByGameId(int id, MoveRequestDto dto) throws SQLException;

    List<String> findAllAvailablePath(int id, String from) throws SQLException;

    boolean isWhiteTurn(int id) throws SQLException;

    boolean isGameOver(int id) throws SQLException;
}
