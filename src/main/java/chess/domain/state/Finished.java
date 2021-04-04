package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.AlphaColumns;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Finished implements GameState {

    private final Map<Position, Piece> chessBoard;

    public Finished(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("게임이 종료되었으므로 움직일 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    @Override
    public GameState terminate() {
        throw new UnsupportedOperationException("이미 끝난 게임입니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Result result() {
        Map<TeamColor, Score> result = teamScores(chessBoard);

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }

    private Map<TeamColor, Score> teamScores(Map<Position, Piece> chessBoard) {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, calculateScore(TeamColor.BLACK, chessBoard));
        result.put(TeamColor.WHITE, calculateScore(TeamColor.WHITE, chessBoard));
        return result;
    }

    private Score calculateScore(TeamColor teamColor, Map<Position, Piece> chessBoard) {
        Score sum = Score.ZERO;
        Map<AlphaColumns, Integer> pawnCount = new HashMap<>();
        for (Map.Entry<Position, Piece> item : chessBoard.entrySet()) {
            sum = getScoreWithoutPawnOnLine(teamColor, sum, pawnCount, item);
        }

        return sum.add(subtractWhenOnSameLine(pawnCount));
    }

    private Score getScoreWithoutPawnOnLine(TeamColor teamColor, Score sum, Map<AlphaColumns, Integer> pawnCount, Map.Entry<Position, Piece> item) {
        if (item.getValue().getColor() == teamColor && item.getValue().isAlive()) {
            sum = sum.add(item.getValue().getScore());
            recordPawns(pawnCount, item);
        }
        return sum;
    }

    private void recordPawns(Map<AlphaColumns, Integer> pawnCount, Map.Entry<Position, Piece> item) {
        if (item.getValue().isPawn()) {
            pawnCount.put(item.getKey().getColumn(),
                    pawnCount.getOrDefault(item.getKey().getColumn(), 0) + 1);
        }
    }

    private Score subtractWhenOnSameLine(Map<AlphaColumns, Integer> pawnCount) {
        return pawnCount.values().stream()
                .filter(number -> number > 1)
                .map(number -> new Score(MINUS_HALF_POINT * number))
                .reduce(Score.ZERO, Score::add);
    }
}
