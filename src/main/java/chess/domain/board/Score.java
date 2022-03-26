package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Score {

    private Map<Team, Double> value = new HashMap<>();

    public Score(Map<Position, Piece> board) {
        for (Team team : Team.values()) {
            value.put(team, calculateScore(board, team));
        }
    }

    private double calculateScore(Map<Position, Piece> board, Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public Map<Team, Double> getValue() {
        return value;
    }
}
