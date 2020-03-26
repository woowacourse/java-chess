package chess.domain.chesspiece;

import java.util.List;

public abstract class ChessPiece {
    private String name;
    private final Team team;
    double score;
    List<Direction> moveDirections;

    public ChessPiece(String name, Team team, double score, List<Direction> moveDirections) {
        this.name = name;
        if (team == Team.BLACK) {
            this.name = name.toUpperCase();
        }
        this.team = team;
        this.score = score;
        this.moveDirections = moveDirections;

    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public String getName() {
        return name;
    }

    public List<Direction> getMoveDirections() {
        return moveDirections;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
