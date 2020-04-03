package chess.domain;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public class TestGameContext implements GameContext {
    private Map<Integer, Game> context = new HashMap<>();

    @Override
    public int addGame(final Player white, final Player black) {
        int gameId = context.size() + 1;
        context.put(gameId, new Game(white, black));
        return gameId;
    }

    @Override
    public boolean isEmpty() {
        return context.size() == 0;
    }

    @Override
    public Game findGameById(final int id) {
        return context.get(id);
    }

    @Override
    public Map<Position, Piece> findBoardById(final int id) {
        return findGameById(id).getBoard().getBoard();
    }

    @Override
    public void resetGameById(final int id) {
        Map<Side, Player> players = context.get(id).getPlayers();
        context.put(id, new Game(players.get(Side.WHITE), players.get(Side.BLACK)));
    }

    @Override
    public void recoverMovesById(final int id, final List<MoveRequestDto> moves) {
        moves.forEach(move -> findGameById(id).move(move.getFrom(), move.getTo()));
    }

    @Override
    public void finishGameById(final int id) {
        findGameById(id).finish();
        context.remove(id);
    }

    @Override
    public double getScoreById(final int id, final Side side) {
        return findGameById(id).getScoreOf(side);
    }

    @Override
    public Map<Integer, Map<Side, Player>> getPlayerContexts() {
        return context.keySet().stream()
            .collect(toMap(Function.identity(), id -> findGameById(id).getPlayers()));
    }

    @Override
    public Map<Integer, Map<Side, Double>> getScoreContexts() {
        return context.keySet().stream()
            .collect(toMap(Function.identity(), this::getScore));
    }

    @Override
    public Map<Side, Double> getScore(final int id) {
        return Arrays.stream(Side.values())
            .filter(side -> side != Side.NONE)
            .collect(toMap(Function.identity(), side -> getScoreById(id, side)));
    }
}
