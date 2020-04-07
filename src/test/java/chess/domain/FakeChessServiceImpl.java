package chess.domain;

import static java.util.stream.Collectors.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.service.ChessService;

public class FakeChessServiceImpl implements ChessService {
    private Map<Integer, Game> context = new HashMap<>();

    @Override
    public Map<Integer, Map<Side, Player>> addGame(final Player white, final Player black) {
        context.put(context.size() + 1, new Game(white, black));
        return context.keySet()
            .stream()
            .collect(toMap(Function.identity(), gameId -> context.get(gameId).getPlayers()));
    }

    @Override
    public Game findGameById(final int id) {
        return context.get(id);
    }

    @Override
    public Board findBoardById(final int id) {
        return findGameById(id).getBoard();
    }

    @Override
    public Board resetGameById(final int id) {
        Map<Side, Player> players = context.get(id).getPlayers();
        context.put(id, new Game(players.get(Side.WHITE), players.get(Side.BLACK)));
        return context.get(id).getBoard();
    }

    @Override
    public boolean finishGameById(final int id) {
        findGameById(id).finish();
        context.remove(id);
        return true;
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
            .collect(toMap(Function.identity(), this::getScoresById));
    }

    @Override
    public Map<Side, Double> getScoresById(final int id) {
        return Arrays.stream(Side.values())
            .filter(side -> side != Side.NONE)
            .collect(toMap(Function.identity(), side -> getScoreById(id, side)));
    }

    @Override
    public boolean addMoveByGameId(final int id, String start, String end) {
        Path path = findBoardById(id).generatePath(Position.of(start), Position.of(end));
        return findGameById(id).move(path.getStart(), path.getEnd());
    }

    @Override
    public List<String> findAllAvailablePath(final int id, final String from) throws SQLException {
        return findGameById(id).findAllAvailablePath(from);
    }

    @Override
    public boolean isWhiteTurn(final int id) throws SQLException {
        return findGameById(id).isWhiteTurn();
    }

    @Override
    public boolean isGameOver(final int id) throws SQLException {
        return findGameById(id).isGameOver();
    }
}
