package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;
import java.util.List;

public abstract class Piece {

    public static final String INVALID_MOVE_EXIST_ALLY = "이동하려는 위치에 아군이 존재합니다.";
    public static final String INVALID_DIRECTION = "해당 기물이 갈 수 없는 방향입니다.";

    private final Team team;
    private final List<Direction> directions;

    public Piece(final Team team, final List<Direction> directions) {
        validateTeam(team);
        this.team = team;
        this.directions = List.copyOf(directions);
    }

    abstract protected void validateTeam(final Team team);

    abstract public void validateMove(final Direction movableDirection, final List<Piece> otherPieces);

    public void validateDirection(final Direction direction) {
        if (directions.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_DIRECTION);
    }

    protected final void validatePiecesTeam(final List<Piece> otherPieces) {
        for (Piece otherPiece : otherPieces) {
            validatePieceTeam(otherPiece);
        }
    }

    protected final void validatePieceTeam(final Piece otherPiece) {
        if (this.team.equals(otherPiece.team)) {
            throw new IllegalArgumentException(INVALID_MOVE_EXIST_ALLY);
        }
    }

    public boolean isSameTeam(final Piece otherPiece) {
        return this.team == otherPiece.team;
    }

    public final Team getTeam() {
        return team;
    }

    public List<Direction> getDirections() {
        return List.copyOf(directions);
    }
}
