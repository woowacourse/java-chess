package chess.domain.piece;

import chess.Strategy.MoveStrategy;
import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.List;

public abstract class Piece {
    private final String name;
    private final Team team;
    private final double score;
    private final MoveStrategy moveStrategy;

    public Piece(String name, Team team, double score, MoveStrategy moveStrategy) {
        this.team = team;
        this.name = convertName(name, team);
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    private String convertName(String name, Team team) {
        if (team.isBlack()) {
            return name;
        }
        return name.toLowerCase();
    }

    public boolean canMove(Position target, Position destination, Board board) {
        return moveStrategy.canMove(target, destination, board);
    }

    public List<Position> searchMovablePositions(Position target) {
        return moveStrategy.searchMovablePositions(target);
    }

    public boolean isSameTeam(Piece piece) {
        return team.equals(piece.getTeam());
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
