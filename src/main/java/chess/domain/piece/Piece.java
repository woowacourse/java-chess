package chess.domain.piece;

import chess.Strategy.MoveStrategy;
import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final String name;
    private final String unicode;
    private final Team team;
    private final double score;
    private final MoveStrategy moveStrategy;

    public Piece(String name, String whiteUnicode, String blackUnicode, Team team, double score, MoveStrategy moveStrategy) {
        this.team = team;
        this.unicode = unicodeSelector(whiteUnicode, blackUnicode, team);
        this.name = convertName(name, team);
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public String unicodeSelector(String whiteUnicode, String blackUnicode, Team team) {
        if (team.equals(Team.BLACK)) {
            return blackUnicode;
        }
        return whiteUnicode;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
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

    public boolean canPromotion(Position position) {
        return isPawn() && position.isDeadLine();
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public Team getNextTurnOwner() {
        return team.getOpposite();
    }

    public double getScore() {
        return score;
    }

    public String getUnicode() {
        return unicode;
    }
}
