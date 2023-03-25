package chess.domain.piece;

import chess.domain.game.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final Team team;
    protected final MovingStrategies movingStrategies;

    public Piece(final Team team, final PieceType pieceType, final MovingStrategies movingStrategies) {
        this.team = team;
        this.movingStrategies = movingStrategies;
        this.pieceType = pieceType;
    }

    public final List<Position> findPath(final Position source, final Position target, final Team targetTeam) {
        final MovingStrategy movingStrategy = movingStrategies.findStrategy(source, target);
        if (targetTeam.isSameColor(team)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }
        return calculatePath(movingStrategy, source, target, targetTeam);
    }

    public abstract List<Position> calculatePath(final MovingStrategy strategy, final Position source, final Position target, final Team targetTeam);

    public abstract boolean isInitialPawn();

    public final boolean isSamePieceTypeAs(final PieceType pieceType) {
        return pieceType == this.pieceType;
    }

    public final boolean isEmpty() {
        return team.isEmpty();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public final Team getTeam() {
        return team;
    }

    public final PieceType getPieceType() {
        return pieceType;
    }

    public boolean isSameTeamWith(final Team team) {
        return this.team == team;
    }

    public double getScore() {
        return pieceType.getScore();
    }
}
