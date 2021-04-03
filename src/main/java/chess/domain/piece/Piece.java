package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.moveStrategy.MoveStrategy;

import java.util.List;

public abstract class Piece {
    private final String name;
    private final Team team;
    private final double score;
    private final boolean promotionChance;
    private final boolean boss;
    private final MoveStrategy moveStrategy;

    public Piece(String name, Team team, double score, MoveStrategy moveStrategy, boolean promotionChance) {
        this(name, team, score, moveStrategy, promotionChance, false);
    }

    public Piece(String name, Team team, double score, MoveStrategy moveStrategy, boolean promotionChance, boolean boss) {
        this.team = team;
        this.name = convertName(name, team);
        this.score = score;
        this.moveStrategy = moveStrategy;
        this.promotionChance = promotionChance;
        this.boss = boss;
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
        return moveStrategy.canMove(target, destination, board);
    }

    public abstract List<Position> searchMovablePositions(Position target);

    public List<Position> movablePositions(Position target, List<Direction> directions) {
        return moveStrategy.searchMovablePositions(target, directions);
    }

    public boolean isDifferentTeam(Piece piece) {
        return !this.team.equals(piece.getTeam());
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public boolean canPromotion(Position position) {
        if (position.isDifferentVertical(Vertical.ONE) && position.isDifferentVertical(Vertical.EIGHT)) {
            return false;
        }
        return promotionChance;
    }

    public boolean isKing() {
        return boss;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return score;
    }
}
