package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final PieceInfo info;
    protected final Team team;

    public Piece(PieceInfo info, Team team) {
        this.info = info;
        this.team = team;
    }

    public abstract void movable(Position from, Position to);

    public abstract Direction findDirection(Position from, Position to);

    public abstract List<Position> findMovablePosition(Position now);

    public final String getName() {
        return info.getName();
    }

    public final boolean isSameTeam(Piece piece) {
        return piece.team == this.team;
    }

    public final boolean isSameTeam(Team team) {
        return team == this.team;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public float getScore() {
        return info.getScore();
    }

    public Team getTeam() {
        return team;
    }

    public PieceInfo getInfo() {
        return info;
    }

    public void validArrive(Piece to, Direction direction) {
        if (Objects.nonNull(to) && isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    @Override
    public String toString() {
        return "Piece{" +
                "info=" + info +
                ", team=" + team +
                '}';
    }
}
