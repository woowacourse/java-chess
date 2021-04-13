package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.score.PieceScore;
import chess.domain.team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private static final int NON_ENTITY_SIGNATURE = -1;

    protected final long id;
    protected final long roomId;
    protected final Team team;
    protected Location location;

    protected Piece(Location location, Team team) {
        this(NON_ENTITY_SIGNATURE, NON_ENTITY_SIGNATURE, team, location);
    }

    protected Piece(long id, long roomId, Team team, Location location) {
        this.id = id;
        this.roomId = roomId;
        this.team = Team.of(team.getValue());
        this.location = Location.of(location.getX(), location.getY());
    }

    public static Piece generatePiece(long id, long roomId, char signature, String team, String location) {
        Team pieceTeam = Team.of(team);
        Location pieceLocation = Location.of(location);
        if (signature == 'r') {
            return Rook.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'k') {
            return King.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'q') {
            return Queen.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'p') {
            return Pawn.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'n') {
            return Knight.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'b') {
            return Bishop.of(id, roomId, pieceTeam, pieceLocation);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
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
        boolean isPieceExistInPath = path
            .stream()
            .anyMatch(oneStep -> board.isPieceExistIn(oneStep));

        if (isPieceExistInPath) {
            throw new IllegalArgumentException("[ERROR] 이동 경로에 말이 존재하므로 이동할 수 없습니다.");
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

    public abstract boolean isKing();

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public long getId() {
        return id;
    }

    public long getRoomId() {
        return roomId;
    }

    public Team getTeam() {
        return Team.of(team.getValue());
    }

    public Location getLocation() {
        return Location.of(location.getX(), location.getY());
    }

    public abstract char getSignature();

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
