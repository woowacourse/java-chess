package chess.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.GameContext;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public class ChessService {
    private GameContext context;

    public ChessService(final GameContext context) {
        this.context = context;
    }

    public Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException {
        return context.getPlayerContexts();
    }

    public Map<Position, Piece> getBoard(int id) throws SQLException {
        return context.findBoardById(id);
    }

    public Map<Position, Piece> resetBoard(int id) throws SQLException {
        context.resetGameById(id);
        return getBoard(id);
    }

    public Map<Integer, Map<Side, Player>> addGameAndGetPlayers() throws SQLException {
        HashMap<Integer, Map<Side, Player>> result = new HashMap<>();

        // TODO: 실제 플레이어 기능 추가
        Player white = new Player("hodol", "password");
        white.setId(1);
        Player black = new Player("pobi", "password");
        black.setId(2);

        int gameId = context.addGame(white, black);
        result.put(gameId, context.findGameById(gameId).getPlayers());
        return result;
    }

    public List<String> findAllAvailablePath(int id, String from) throws SQLException {
        return context.findGameById(id).findAllAvailablePath(from);
    }

    public Map<Integer, Map<Side, Double>> getScoreContexts() throws SQLException {
        return context.getScoreContexts();
    }

    public Map<Side, Double> getScore(final int id) throws SQLException {
        return context.getScoresById(id);
    }

    public boolean isWhiteTurn(final int id) throws SQLException {
        return context.findGameById(id).isWhiteTurn();
    }

    public boolean move(final int id, MoveRequestDto dto) throws SQLException {
        boolean movable = context.findGameById(id).move(dto.getFrom(), dto.getTo());
        if (movable) {
            context.addMoveByGameId(id, dto);
        }
        return movable;
    }

    public boolean finishGame(final int id) throws SQLException {
        context.finishGameById(id);
        return true;
    }

    public boolean isGameOver(final int id) throws SQLException {
        return context.findGameById(id).isGameOver();
    }
}
