package chess.domain.state;

import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.AlphaColumns;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public abstract class Game implements GameState {
    private double MINUS_HALF_POINT = -0.5;

    protected Result calculateResult(Map<Position, Piece> chessBoard) {
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
            sum = addScore(teamColor, sum, pawnCount, item);
        }

        return sum.add(subtractWhenOnSameLine(pawnCount));
    }

    private Score addScore(TeamColor teamColor, Score sum, Map<AlphaColumns, Integer> pawnCount, Map.Entry<Position, Piece> item) {
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
