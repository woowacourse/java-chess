package chess.domain.piece;

import chess.domain.board.Score;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final String name;
    private final Score score;
    protected final Team team;

    public Piece(String name, Score score, Team team) {
        this.name = name;
        this.score = score;
        this.team = team;
    }

    public abstract void movable(Position from, Position to);

    public abstract Direction findDirection(Position from, Position to);

    public abstract List<Position> findMovablePosition(Position now);

    public final String getName() {
        return team.changeCaseSensitive(name);
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

    public boolean isStep() {
        return true;
    }

    public float getScore() {
        return score.getValue();
    }

    public void validArrive(Piece to, Direction direction) {
        if (Objects.nonNull(to) && isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    @Override
    public String toString() {
        return "Piece{" +
                "name='" + team.changeCaseSensitive(name) + '\'' +
                ", score=" + score +
                ", team=" + team +
                '}';
    }
}
