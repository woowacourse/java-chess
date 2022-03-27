package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {

    private final Map<Team, Double> value = new HashMap<>();

    public Score(Map<Position, Piece> board) {
        for (Team team : Team.values()) {
            value.put(team, calculateScore(board, team) + calculatePawnScore(board, team));
        }
    }

    private double calculateScore(Map<Position, Piece> board, Team team) {
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
                .count() / 2;

        return count * -0.5;
    }

    private List<Position> findPawnPositions(Map<Position, Piece> board, Team team) {
        return board.keySet()
                .stream()
                .filter(key -> board.get(key).isPawn() && board.get(key).isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Team, Double> getValue() {
        return value;
    }
}
