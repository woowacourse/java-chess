package chess.domain.piece;

import chess.Strategy.MoveStrategy;
import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.List;
import java.util.Objects;

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
        return Objects.equals(team, piece.getTeam());
    }

    public boolean isSameTeam(Team team) {
        return Objects.equals(this.team, team);
    }

    public boolean isDifferentTeam(Piece piece) {
        return !Objects.equals(team, piece.getTeam());
    }

    public boolean isKing() {
        return score == 0;
    }

    public boolean canPromotion(Position position) {
        return score == 1 && position.isDeadLine();
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
