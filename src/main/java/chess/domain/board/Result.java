package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private static final double CONVERT_PAWN_SCORE = 0.5;
    private static final int DUPLICATION_REMOVE_VALUE = 2;

    private final Map<Team, Double> value = new HashMap<>();
    private final List<Team> winnerResult;

    public Result(Map<Position, Piece> board) {
        for (Team team : Team.values()) {
            value.put(team, calculateResult(board, team) + calculatePawnScore(board, team));
        }

        winnerResult = findWinTeam();
    }

    private double calculateResult(Map<Position, Piece> board, Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePawnScore(Map<Position, Piece> board, Team team) {
        List<Position> pawns = findPawnPositions(board, team);

        long count = pawns.stream()
                .flatMap(pawn -> pawns
                        .stream()
                        .filter(target -> (!target.equals(pawn)) && target.isSameColumn(pawn)))
                .count() / DUPLICATION_REMOVE_VALUE;

        return count * -CONVERT_PAWN_SCORE;
    }

    private List<Position> findPawnPositions(Map<Position, Piece> board, Team team) {
        return board.keySet()
                .stream()
                .filter(key -> board.get(key).isPawn() && board.get(key).isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Team> findWinTeam() {
        return Team.findWinner(value.get(Team.WHITE), value.get(Team.BLACK));
    }

    public Map<Team, Double> getValue() {
        return Map.copyOf(value);
    }

    public List<Team> getWinnerResult() {
        return List.copyOf(winnerResult);
    }
}
