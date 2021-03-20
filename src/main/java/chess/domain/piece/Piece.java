package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.moveStrategy.CanMoveStrategy;

import java.util.List;

public abstract class Piece {
    private final String name;
    private final Team team;
    private final double score;

    private final CanMoveStrategy canMoveStrategy;

    public Piece(String name, Team team, double score, CanMoveStrategy canMoveStrategy) {
        this.team = team;
        this.name = convertName(name, team);
        this.score = score;
        this.canMoveStrategy = canMoveStrategy;
    }

    private String convertName(String name, Team team) {
        if (team.isBlack()) {
            return name;
        }
        return name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public boolean isMovable(Position target, Position destination, Board board) {
        return canMoveStrategy.canMove(target, destination, board);
    }

    public abstract List<Position> searchMovablePositions(Position target);

    public boolean isSameTeam(Piece piece) {
        return this.team.equals(piece.getTeam());
    }

    public boolean isDifferentTeam(Piece piece) {
        return !this.team.equals(piece.getTeam());
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
