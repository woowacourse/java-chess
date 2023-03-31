package chess.dao;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.Game;
import chess.domain.game.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class FakeGameDao implements GameDao {

    private final Map<Integer, Map<Position, Piece>> pieces;
    private final Map<Integer, Team> turns;
    private final Map<Integer, Boolean> gameStates;

    public FakeGameDao() {
        this.pieces = new HashMap<>();
        this.turns = new HashMap<>();
        this.gameStates = new HashMap<>();
    }

    @Override
    public Integer save(Game game) {
        int id = pieces.size();
        put(id, game);
        return id;
    }

    @Override
    public void put(Integer gameId, Game game) {
        pieces.put(gameId, game.getPieces());
        turns.put(gameId, game.getTurn());
        gameStates.put(gameId, game.isFinished());
    }

    @Override
    public Map<Position, Piece> findPiecesBy(Integer gameId) {
        return pieces.get(gameId);
    }

    @Override
    public Team findTurnBy(Integer gameId) {
        return turns.get(gameId);
    }

    @Override
    public void end(Integer gameId) {
        gameStates.put(gameId, true);
    }

    @Override
    public boolean hasUnfinished() {
        return gameStates.containsValue(false);
    }

    @Override
    public Integer findIdOfLastUnfinished() {
        return gameStates.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
