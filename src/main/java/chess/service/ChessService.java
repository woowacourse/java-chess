package chess.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.GameContext;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;

public class ChessService {
    private GameContext context;

    public ChessService(final GameContext context) {
        this.context = context;
    }

    public Map<Integer, Map<Side, Player>> getPlayerContexts() {
        return context.getPlayerContexts();
    }

    public Map<Position, Piece> getBoard(int id) {
        return context.findBoardById(id);
    }

    public Map<Position, Piece> resetBoard(int id) {
        context.resetGameById(id);
        return getBoard(id);
    }

    public Map<Integer, Map<Side, Player>> addGameAndGetPlayers() {
        HashMap<Integer, Map<Side, Player>> result = new HashMap<>();

        // TODO: 임시로 한 것
        Player white = new Player("hodol", "password");
        Player black = new Player("pobi", "password");

        int gameId = context.addGame(white, black);
        result.put(gameId, context.findGameById(gameId).getPlayers());
        return result;
    }

    public List<String> findAllAvailablePath(int id, String from) {
        return context.findGameById(id).findAllAvailablePath(from);
    }

    public Map<Integer, Map<Side, Double>> getScoreContexts() {
        return context.getScoreContexts();
    }

    public Map<Side, Double> getScore(final int id) {
        return context.getScore(id);
    }

    public boolean isWhiteTurn(int id) {
        return context.findGameById(id).isWhiteTurn();
    }

    public boolean move(int id, String from, String to) {
        return context.findGameById(id).move(from, to);
    }
}
