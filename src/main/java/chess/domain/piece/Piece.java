package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Team team;
    protected Location location;

    protected Piece(final Location location, final Team team) {
        this.location = location;
        this.team = team;
    }

    public void moveTo(Location target, Board board) {
        validateTargetIsNotSameTeam(target, board);
        validateMovingAbilityToTarget(target);
        validateNotExistObjectInPath(findPathTo(target), board);
        validatePawnMovable(target, board);
        removeIfExist(target, board);
        move(target);
    }

    public final void validateTargetIsNotSameTeam(Location target, Board board) {
        if (board.isPieceExistIn(target) && this.isSameTeam(board.find(target))) {
            throw new IllegalArgumentException("목표 위치에 같은 팀의 말이 있습니다.");
        }
    }

    private final boolean isSameTeam(Piece other) {
        return (isBlackTeam() && other.isBlackTeam())
            || (isWhiteTeam() && other.isWhiteTeam());
    }

    public final boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    private final boolean isBlackTeam() {
        return team.isBlack();
    }

    private final boolean isWhiteTeam() {
        return team.isWhite();
    }

    public final boolean isExistIn(Location location) {
        return this.location.equals(location);
    }

    protected final void validateNotExistObjectInPath(List<Location> path, Board board) {
        for (Location location : path) {
            if (board.isPieceExistIn(location)) {
                throw new IllegalArgumentException("[ERROR] 이동 경로에 말이 존재하므로 이동할 수 없습니다.");
            }
        }
    }

    protected List<Location> findPathTo(Location target) {
        List<Location> path = new ArrayList<>();
        int subX = location.subtractX(target);
        int subY = location.subtractY(target);
        int dx = subX == 0 ? 0 : subX / Math.abs(subX);
        int dy = subY == 0 ? 0 : subY / Math.abs(subY);

        Location next = location.moveByStep(dx, dy);
        while(!next.equals(target)) {
            path.add(next);
            next = next.moveByStep(dx, dy);
        }
        return path;
    }

    protected void validatePawnMovable(Location target, Board board) { }

    private void removeIfExist(Location target, Board board) {
        if (board.isPieceExistIn(target)) {
            Piece targetPiece = board.find(target);
            board.remove(targetPiece);
        }
    }

    private final void move(final Location target) {
        this.location = target;
    }

    protected abstract void validateMovingAbilityToTarget(Location target);

    public abstract PieceScore pieceScore();

    public abstract boolean isPawn();

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team && Objects.equals(location, piece.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, location);
    }
}
