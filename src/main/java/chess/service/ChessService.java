package chess.service;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import chess.domain.ChessContext;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;

public class ChessService {
    private Map<Long, ChessContext> boards = new HashMap<>();

    public ChessService() {
        boards.put(1L, new ChessContext(Board.init()));
        boards.put(2L, new ChessContext(Board.init()));
        boards.put(3L, new ChessContext(Board.init()));
        boards.put(4L, new ChessContext(Board.init()));
    }

    public ChessContext getContext(final Long id) {
        return boards.get(id);
    }

    public Map<Long, Map<Side, String>> getBoards() {
        return boards.keySet().stream()
            .collect(toMap(Function.identity(), id -> getContext(id).getPlayers()));
    }

    public Map<Position, Piece> getBoard(Long id) {
        return getContext(id).getBoard().getBoard();
    }

    public Map<Position, Piece> resetBoard(Long id) {
        boards.put(id, new ChessContext(Board.init()));
        return getBoard(id);
    }

    public Map<Long, Map<Side, String>> addBoard() {
        HashMap<Long, Map<Side, String>> result = new HashMap<>();
        Long maxIndex = boards.keySet()
            .stream()
            .reduce(0L, Math::max);
        boards.put(maxIndex + 1, new ChessContext(Board.init()));
        result.put(maxIndex + 1, boards.get(maxIndex + 1).getPlayers());
        return result;
    }

    public List<String> findAllAvailablePath(Long id, String from) {
        return getContext(id).findAllAvailablePath(from);
    }

    public Map<Long, Map<Side, Double>> getScores() {
        return boards.keySet().stream()
            .collect(toMap(Function.identity(), this::getScore));
    }

    public Map<Side, Double> getScore(final Long id) {
        return Arrays.stream(Side.values())
            .filter(side -> side != Side.NONE)
            .collect(toMap(Function.identity(), side -> getContext(id).getJudge().calculateScore(side)));
    }

    public boolean isWhiteTurn(Long id) {
        return getContext(id).isWhiteTurn();
    }

    public boolean move(Long id, String from, String to) {
        return getContext(id).move(from, to);
    }
}
